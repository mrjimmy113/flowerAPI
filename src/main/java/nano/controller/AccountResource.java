package nano.controller;

import java.util.List;

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

import javassist.NotFoundException;
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
			tokenDto.setRole(acc.getRole());

		} else {
			httpStatus = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<TokenDTO>(tokenDto, httpStatus);

	}
	
	@GetMapping(value = "/admin/acc",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
	
	@GetMapping(value = "/admin/acc/search",produces = MediaType.APPLICATION_JSON_VALUE)
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


	@DeleteMapping("/admin/acc/{id}")
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

	@PutMapping("/admin/acc/updateAccountRole")
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
	
	
	
	@GetMapping("/user/acc/getAccountInfo")
	public Account getAccountInfo() {
		return service.getAccountInfor();
	}
	
	@PutMapping("/forgetPass")
	public boolean forgetPass(@RequestParam String email) {
		boolean valid = false;
		valid = service.forgetPass(email);
		
		return valid;
	}
	
	@PostMapping("/user/acc/updateProfile")
	public Account updateProle(@RequestBody Account account) {
		System.out.println("update Profile");
		service.updateAccountInfor(account);
		return service.getAccountInfor();
	}
	
	@PostMapping("/user/acc/changePass")
	public Integer changePass(@RequestParam("oldP") String oldP, @RequestParam("newP") String newP) {
		HttpStatus status = null;
		try {
			service.changePassword(oldP, newP);
			status = HttpStatus.OK;
		} catch (NotFoundException e) {
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return status.value();
	}
	
}
