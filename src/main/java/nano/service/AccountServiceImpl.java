package nano.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;
import nano.dto.GetAllDTO;
import nano.entity.Account;
import nano.exception.ResourceNotFoundException;
import nano.repository.AccountRepository;
import nano.utils.HelperSendEmail;

@Service
public class AccountServiceImpl implements AccountService {

	private static final int PAGEMAXSIZE = 9;

	@Autowired
	AccountRepository repository;

	@Autowired
	JwtService jwt;

	@Override
	public String login(String username, String password) {
		Account acc = repository.findByUsernameAndPassword(username, password);
		if (acc != null) {
			return jwt.generateTokenLogin(acc.getUsername(), acc.getRole());
		}
		return "";

	}

	@Override
	public Account getAccountByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Transactional
	@Override
	public List<Account> all() {
		return repository.myFindAll();
	}

	@Transactional
	@Override
	public Account newAccount(Account newAccount) {
		return repository.save(newAccount);
	}

	@Transactional
	@Override
	public Account one(int id) throws ResourceNotFoundException {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	@Override
	public Account replaceAccount(Account newAccount, int id) {
		return repository.findById(id).map(user -> {
			return repository.save(newAccount);
		}).orElseGet(() -> {
			newAccount.setId(id);
			return repository.save(newAccount);
		});
	}

	@Transactional
	@Override
	public boolean deleteAccount(int id) {

		boolean valid = false;
		try {
			repository.deleteById(id);
			valid = true;
		} catch (Exception e) {
			valid = false;
		}

		return valid;
	}

	@Transactional
	@Override
	public boolean checkUsernameExist(String username) {

		Account account = new Account();
		account = repository.findByUsername(username);

		if (account != null) {
			return true;
		}

		return false;
	}
	
	@Transactional
	@Override
	public boolean checkEmailExist(String email) {

		Account account = new Account();
		account = repository.findByEmail(email);

		if (account != null) {
			return true;
		}

		return false;
	}

	@Transactional
	@Override
	public void updateAccountRole(String username, String role) {

		Account account = new Account();
		account = repository.findByUsername(username);
		if (account != null) {
			account.setRole(role);
		}

	}

	@Transactional
	@Override
	public boolean updateAccountPassword(String username, String pass) {
		Account account = new Account();
		account = repository.findByUsername(username);

		if (account != null) {

			account.setPassword(pass);

			return true;
		}

		return false;
	}

	@PostFilter("filterObject.username != authentication.principal.username")
	@Override
	public List<Account> searchByNamePage(String name, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, PAGEMAXSIZE);
		Page<Account> page = repository.findByUsernameContaining(name, pageable);
		List<Account> listAccount = page.getContent();
		for (Account account : listAccount) {
			account.setPassword(null);
			account.setOrders(null);
		}
		return listAccount;
	}

	
	@Override
	public GetAllDTO<Account> findAllItem(String searchTerm) {
		Pageable pageable = PageRequest.of(0, PAGEMAXSIZE);
		Page<Account> page = repository.findByUsernameContaining(searchTerm, pageable);
		List<Account> listAccount = page.getContent();
		for (Account account : listAccount) {
			account.setPassword(null);
			account.setOrders(null);
		}
		GetAllDTO<Account> acc = new GetAllDTO<Account>();
		acc.setList(this.searchByNamePage(searchTerm, 1));
		acc.setMaxPage(page.getTotalPages());
		return acc;
	}

	@Transactional
	@Override
	public boolean forgetPass(String email) {

		Account account = new Account();
		boolean valid = false;
		account = repository.findByEmail(email);
		if (account != null) {
			valid = true;

			byte[] array = new byte[7];
			new Random().nextBytes(array);
			String generatedString = new String(array, Charset.forName("UTF-8"));
			account.setEmail(generatedString);
			HelperSendEmail sendMail = new HelperSendEmail();
			sendMail.sendMail(email, "You have request a new Password", "Your new password is: " + generatedString);

			return valid;
		}

		return valid;
	}

	@Transactional(readOnly = true)
	@Override
	public Account getAccountInfor() {
		Account acc = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			acc = repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			acc.setOrders(null);
			acc.setPassword(null);
		}

		return acc;
	}

	
	@Transactional
	@Override
	public void updateAccountInfor(Account account) {
		Account tmp = repository.findById(account.getId()).get();
		if(tmp != null) {
			tmp.setEmail(account.getEmail());
			tmp.setName(account.getName());
			tmp.setSurname(account.getSurname());
			tmp.setTelephone(account.getTelephone());
			System.out.println(tmp.getPassword());
			repository.save(tmp);
		}
	}
	
	@Override
	public void changePassword(String oldP, String newP) throws NotFoundException {
		Account acc = null;
		if(oldP.equals(newP)) return;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			acc = repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			if(!acc.getPassword().equals(oldP)) {
				throw new NotFoundException("msg");
			}else {
				acc.setPassword(newP);
				repository.save(acc);
			}
		}

	}
	
	
}
