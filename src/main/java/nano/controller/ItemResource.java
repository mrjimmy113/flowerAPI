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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nano.dto.GetAllDTO;
import nano.dto.ItemDTO;
import nano.service.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemResource {

	@Autowired
	ItemService service;

	@GetMapping("/all")
	public ResponseEntity<List<ItemDTO>> findAll() {
		List<ItemDTO> dtos = null;
		HttpStatus status = null;
		try {
			dtos = service.getAll();
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<ItemDTO>>(dtos, status);
	}
	
	@GetMapping("/one")
	public ResponseEntity<ItemDTO> findOne(@RequestParam("id") int id) {
		HttpStatus status = null;
		ItemDTO dto = null;
		try {
			dto = service.findOne(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<ItemDTO>(dto,status);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GetAllDTO<ItemDTO>> getAllItem(@RequestParam String searchTerm) {
		GetAllDTO<ItemDTO> item;
		try {	
			item = service.findAllItem(searchTerm);
		} catch (Exception e) {
			return new ResponseEntity<GetAllDTO<ItemDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<GetAllDTO<ItemDTO>>(item, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemDTO>> searchWithPage(@RequestParam String searchTerm, @RequestParam int pageNum) {
		List<ItemDTO> items = null;
		HttpStatus status = null;
		try {	
			items = service.searchByNamePage(searchTerm, pageNum);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<ItemDTO>>(items,status);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Integer> create(@RequestBody ItemDTO dto) {
		HttpStatus status;
		System.out.println("CREATE");
		try {
			service.addItem(dto.toItem());
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(), status);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Integer> update(@RequestBody ItemDTO dto) {
		HttpStatus status;
		try {
			service.modifyItem(dto.toItem());
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(), status);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") Integer id) {
		HttpStatus status;
		try {
			System.out.println("Delete");
			service.removeItem(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(), status);
	}

}
