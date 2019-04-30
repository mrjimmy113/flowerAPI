package nano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nano.dto.GetAllDTO;
import nano.dto.ItemImportDTO;
import nano.service.ItemImportService;

@RestController
@RequestMapping(value = "/itemImport")
public class ItemImportResource {

	@Autowired
	ItemImportService service;
	
	@PostMapping
	public ResponseEntity<Integer> newImport(@RequestBody ItemImportDTO dto) {
		HttpStatus status = null;
		try {
			service.newImport(dto);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(),status);
	}
	@GetMapping()
	public ResponseEntity<GetAllDTO<ItemImportDTO>> getAll(@RequestParam Long from, @RequestParam Long to) {
		HttpStatus status = null;
		GetAllDTO<ItemImportDTO> dto = null;
		try {
			dto = service.search(from, to);
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<GetAllDTO<ItemImportDTO>>(dto,status);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<ItemImportDTO>> getPage (@RequestParam Long from, @RequestParam Long to, @RequestParam int numPage) {
		HttpStatus status = null;
		List<ItemImportDTO> dtos = null;
		try {
			dtos = service.getPage(from, to, numPage);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_GATEWAY;
		}
		return new ResponseEntity<List<ItemImportDTO>>(dtos, status);
	}
}
