package nano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nano.dto.AccountDTO;
import nano.dto.TokenDTO;
import nano.service.AccountService;

@Controller
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
}
