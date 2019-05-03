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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nano.dto.AccountDTO;
import nano.dto.TokenDTO;
import nano.entity.Account;
import nano.service.AccountService;
import nano.exception.ResourceNotFoundException;

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

		} else {
			httpStatus = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<TokenDTO>(tokenDto, httpStatus);

	}
	
	@GetMapping("/acc")
	public List<Account> all() {
		return service.all();
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

	@DeleteMapping("/delete/{id}")
	void deleteAccount(@PathVariable int id) {
		service.deleteAccount(id);
	}

	@GetMapping("/checkUsernameExist/{username}")
	public boolean checkUsernameExist(@PathVariable("username") String username) {
		return service.checkUsernameExist(username);
	}

	@GetMapping("/updateAccountRole/{username}&{role}")
	public boolean updateAccountRole(@PathVariable("username") String username, @PathVariable("role") String role) {
		return service.updateAccountRole(username, role);
	}
}
