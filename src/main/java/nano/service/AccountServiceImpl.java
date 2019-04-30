package nano.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nano.entity.Account;
import nano.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository repository;
	
	@Autowired
	JwtService jwt;
	

	@Override
	public String login(String username, String password) {
		Account acc = repository.findByUsernameAndPassword(username, password);
		if(acc != null) {
			return jwt.generateTokenLogin(acc.getUsername(), acc.getRole());
		}
		return "";
		
	}


	@Override
	public Account getAccountByUsername(String username) {
		return repository.findByUsername(username);
	}
	
}
