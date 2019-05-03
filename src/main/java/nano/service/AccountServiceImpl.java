package nano.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nano.entity.Account;
import nano.repository.AccountRepository;
import nano.exception.ResourceNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

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
		return repository.findAll();
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
	public void deleteAccount(int id) {
		repository.deleteById(id);
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
	public boolean updateAccountRole(String username, String role) {

		Account account = new Account();
		account = repository.findByUsername(username);

		if (account != null) {

			account.setRole(role);

			return true;
		}

		return false;
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

	@Transactional
	@Override
	public Map<String, Object> getAccountInfo(String username) {

		Account account = repository.findByUsername(username);
		
		Map<String, Object> obj = new HashMap<String, Object>();
		
		obj.put("name", account.getName());
		obj.put("surname", account.getSurname());
		obj.put("email", account.getEmail());
		obj.put("telephone", account.getTelephone());
		obj.put("address", account.getAddress());

		return obj;
	}
}
