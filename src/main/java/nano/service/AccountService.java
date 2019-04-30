package nano.service;

import nano.entity.Account;

public interface AccountService  {
	String login(String username, String password);
	
	Account getAccountByUsername(String username);
}
