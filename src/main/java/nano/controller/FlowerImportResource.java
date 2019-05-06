package nano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nano.dto.FlowerImportDTO;
import nano.dto.GetAllDTO;
import nano.service.FlowerImportService;

@RestController
@RequestMapping(value = "/flowerImport")
public class FlowerImportResource {
	
	@Autowired
	FlowerImportService service;

	@PostMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public ResponseEntity<Integer> newImport(@RequestBody FlowerImportDTO dto) {
		HttpStatus status = null;
		try {
			service.newImport(dto);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(), status);	
	}
	
	@GetMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public ResponseEntity<GetAllDTO<FlowerImportDTO>> getAll(@RequestParam Long from, @RequestParam Long to) {
		HttpStatus status = null;
		System.out.println("FlowerImport");
		GetAllDTO<FlowerImportDTO> dto = null;
		try {
			dto = service.search(from, to);
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<GetAllDTO<FlowerImportDTO>>(dto,status);
	}
	
	@GetMapping(value = "/search")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public ResponseEntity<List<FlowerImportDTO>> getPage (@RequestParam Long from, @RequestParam Long to, @RequestParam int numPage) {
		HttpStatus status = null;
		List<FlowerImportDTO> dtos = null;
		try {
			dtos = service.getPage(from, to, numPage);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_GATEWAY;
		}
		return new ResponseEntity<List<FlowerImportDTO>>(dtos, status);
	}
	
}
