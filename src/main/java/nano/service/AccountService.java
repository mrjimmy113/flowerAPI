package nano.service;

import java.util.List;

import nano.entity.Account;
import nano.exception.ResourceNotFoundException;

public interface AccountService  {
	String login(String username, String password);
	
	Account getAccountByUsername(String username);
	
	List<Account> all();

	Account newAccount(Account newAccount);

	Account one(int id) throws ResourceNotFoundException;

	Account replaceAccount(Account newAccount, int id);

	void deleteAccount(int id);
	
	boolean checkUsernameExist(String username);
	
	boolean updateAccountRole(String username, String role);
}
