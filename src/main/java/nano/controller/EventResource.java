package nano.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import nano.dto.EventDTO;
import nano.entity.Event;
import nano.service.EventService;

@RestController
@RequestMapping(value = "/event")
public class EventResource {

	@Autowired
	EventService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<EventDTO>> getAllEvent() {
		List<Event> items = service.findAllEvent();
		List<EventDTO> dtos = new ArrayList<EventDTO>();
		try {
			for (Event ev : items) {
				dtos.add(ev.toDTO());
			}
			
		} catch (Exception e) {
			return new ResponseEntity<List<EventDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<EventDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Integer> create(@RequestBody EventDTO dto) {
		HttpStatus status;
		try {
			service.addEvent(dto.toEntity());
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(),status);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Integer> update(@RequestBody EventDTO dto) {
		HttpStatus status;
		try {
			service.modifyEvent(dto.toEntity());
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(),status);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") Integer id) {
		HttpStatus status;
		try {
			service.removeEvent(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(),status);
	}
	
	
}
