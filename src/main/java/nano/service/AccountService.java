package nano.service;

import java.util.List;
import java.util.Map;

import nano.dto.GetAllDTO;
import nano.entity.Account;
import nano.exception.ResourceNotFoundException;

public interface AccountService  {
	String login(String username, String password);
	
	Account getAccountByUsername(String username);
	
	List<Account> all();

	Account newAccount(Account newAccount);

	Account one(int id) throws ResourceNotFoundException;

	Account replaceAccount(Account newAccount, int id);

	boolean deleteAccount(int id);
	
	boolean checkUsernameExist(String username);
	
	boolean checkEmailExist(String email);
	
	void updateAccountRole(String username, String role);
	
	boolean updateAccountPassword(String username, String pass);
	
	Map<String, Object> getAccountInfo(String username);
	
	List<Account> searchByNamePage(String name,int pageNum);
	GetAllDTO<Account> findAllItem(String searchTerm);
	
	boolean forgetPass(String email);
}
