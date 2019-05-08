package nano.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import nano.dto.FlowerDTO;
import nano.dto.GetAllDTO;
import nano.service.FlowerService;

@Controller

public class FlowerResource {

	Logger log = Logger.getLogger(FlowerResource.class.getName());

	@Autowired
	private FlowerService service;

	@GetMapping("/flower/all")
	public ResponseEntity<List<FlowerDTO>> findAll() {
		List<FlowerDTO> dtos = null;
		HttpStatus status = null;
		try {
			dtos = service.getAll();
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<FlowerDTO>>(dtos, status);
	}
	
	@GetMapping("/flower/one")
	public ResponseEntity<FlowerDTO> findOne(@RequestParam("id") int id) {
		FlowerDTO dto = null;
		HttpStatus status = null;
		try {
			dto = service.findOne(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<FlowerDTO>(dto,status);
	}

	@RequestMapping(value = "/flower", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public ResponseEntity<GetAllDTO<FlowerDTO>> findAll(@RequestParam String searchTerm) {
		HttpStatus status = null;
		GetAllDTO<FlowerDTO> dto = null;
		try {
			dto = service.findAllFlower(searchTerm);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<GetAllDTO<FlowerDTO>>(dto, status);
	}

	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public ResponseEntity<List<FlowerDTO>> searchWithPage(@RequestParam String searchTerm, @RequestParam int pageNum) {
		List<FlowerDTO> dtos = null;
		HttpStatus status = null;
		try {
			dtos = service.searchByNamePage(searchTerm, pageNum);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<FlowerDTO>>(dtos, status);
	}

	@RequestMapping(value = "/flower", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
	@ResponseBody
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Integer> create(@RequestParam("file") MultipartFile file, @RequestParam("dto") String dto) {
		try {
			
			service.addFlower(file,dto);
		} catch (Exception e) {
			log.info("ERROR: " + e.getMessage());
			return new ResponseEntity<Integer>(400, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Integer>(201, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/flower/update", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
	@ResponseBody
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public ResponseEntity<Integer> updateFile(@RequestParam(name = "file", required = false) MultipartFile file,
			@RequestParam("dto") String dto) {

		try {
			service.modifyFlower(file, dto);
		
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(400, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Integer>(200, HttpStatus.OK);
	}



	@RequestMapping(value = "/flower/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
		try {
			service.remove(id);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(400, HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<Integer>(200, HttpStatus.OK);
	}

}
