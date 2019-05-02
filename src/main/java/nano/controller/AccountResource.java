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

@RestController
public class AccountResource {
	
	@Autowired
	AccountService service;

	@RequestMapping(value = "/login", method = RequestMethod.POST ,
			consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<TokenDTO> login (@RequestBody AccountDTO dto) {
		String token = service.login(dto.getUsername(), dto.getPassword());
		HttpStatus httpStatus = null;
		TokenDTO tokenDto = new TokenDTO();
		if(!token.isEmpty()) {
			httpStatus = HttpStatus.OK;
			tokenDto.setTokenValue(token);
			
		}else {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<TokenDTO>(tokenDto,httpStatus);
	
	}
	
	@GetMapping("/all")
	public List<Account> all() {
		return service.all();
	}

	@PostMapping("/add")
	public Account newAccount(@RequestBody Account newAccount) {
		return service.newAccount(newAccount);
	}

	@GetMapping("get/{id}")
	public Account one(@PathVariable("id") int id) {
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
}
