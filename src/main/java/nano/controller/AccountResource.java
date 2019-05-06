package nano.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nano.dto.AccountDTO;
import nano.dto.GetAllDTO;
import nano.dto.TokenDTO;
import nano.entity.Account;
import nano.exception.ResourceNotFoundException;
import nano.service.AccountService;

@RestController
public class AccountResource {

	@Autowired
	AccountService service;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<TokenDTO> login(@RequestBody AccountDTO dto) {
		String token = service.login(dto.getUsername(), dto.getPassword());
		HttpStatus httpStatus = null;
		TokenDTO tokenDto = new TokenDTO();
		if (!token.isEmpty()) {
			httpStatus = HttpStatus.OK;
			tokenDto.setTokenValue(token);
			Account acc = service.getAccountByUsername(dto.getUsername());
			tokenDto.setName(acc.getName() + " "  + acc.getSurname());

		} else {
			httpStatus = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<TokenDTO>(tokenDto, httpStatus);

	}
	
	@GetMapping(value = "/acc",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GetAllDTO<Account>> getAllItem(@RequestParam String searchTerm) {
		GetAllDTO<Account> item = null;
		HttpStatus status = null;
		try {	
			item = service.findAllItem(searchTerm);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<GetAllDTO<Account>>(item, status);
	}
	
	@GetMapping(value = "/acc/search",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Account>> searchWithPage(@RequestParam String searchTerm, @RequestParam int pageNum) {
		List<Account> items = null;
		HttpStatus status = null;
		try {	
			items = service.searchByNamePage(searchTerm, pageNum);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<Account>>(items,status);
	}
	
	@GetMapping("/all")
	public List<Account> all() {
		
		List<Account> listAccount = service.all();
		
		return listAccount;
	}

	@PostMapping("/acc")
	public Integer newAccount(@RequestBody Account newAccount) {
		HttpStatus status = null;
		try {
			service.newAccount(newAccount);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return status.value();
	}

	@GetMapping("get/{id}")
	public Account one(@PathVariable("id") int id) throws ResourceNotFoundException {
		return service.one(id);
	}

	@PutMapping("/put/{id}")
	public Account replaceAccount(@RequestBody Account newAccount, @PathVariable("id") int id) {
		return service.replaceAccount(newAccount, id);
	}

	@DeleteMapping("/acc/{id}")
	public Integer deleteAccount(@PathVariable int id) {			
		HttpStatus status = null;
		try {
			service.deleteAccount(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return status.value();
		
	}

	@GetMapping("/acc/checkUsernameExist/{username}")
	public boolean checkUsernameExist(@PathVariable("username") String username) {
		return service.checkUsernameExist(username);
	}
	
	@GetMapping("/acc/checkUsernameExist")
	public boolean checkEmailExist(@RequestParam String email) {
		return service.checkUsernameExist(email);
	}

	@PutMapping("/acc/updateAccountRole")
	public Integer updateAccountRole(@RequestParam("username") String username, @RequestParam("role") String role) {		
		HttpStatus status = null;
		try {
			service.updateAccountRole(username, role);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}

		return status.value();
	}
	
//	@PutMapping("/updateAccountPassword?username={username}&pass={pass}")
//	public Integer updateAccountPassword(@PathVariable("username") String username, @PathVariable("pass") String pass) {		
//		HttpStatus status = null;
//		boolean valid;
//		valid = service.updateAccountRole(username, pass);
//
//		if (valid == true) {
//			status = HttpStatus.OK;
//		} else {
//			status = HttpStatus.BAD_REQUEST;
//		}
//
//		return status.value();
//	}
	
	@GetMapping("/getAccountInfo/{username}")
	public Map<String, Object> getAccountInfo(@PathVariable("username") String username) {

		Map<String, Object> obj = new HashMap<String, Object>();
		
		obj = service.getAccountInfo(username);
	    
		return obj;

	}
	
	@PutMapping("/forgetPass")
	public boolean forgetPass(@RequestParam String email) {
		boolean valid = false;
		valid = service.forgetPass(email);
		
		return valid;
	}
}
