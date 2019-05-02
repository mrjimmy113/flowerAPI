package nano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nano.entity.Evaluate;
import nano.service.EvaluateService;

@RestController
@RequestMapping("/api/evaluate")
public class EvaluateResource {

	@Autowired
	private EvaluateService service;

	@GetMapping("/all")
	public List<Evaluate> all() {
		return service.all();
	}

	@PostMapping("/add")
	public Evaluate newEvaluate(@RequestBody Evaluate newEvaluate) {
		return service.newEvaluate(newEvaluate);
	}

	@GetMapping("get/{id}")
	public Evaluate one(@PathVariable("id") int id) {
			return service.one(id);
	}
	
	@PutMapping("/put/{id}")
	public Evaluate replaceEvaluate(@RequestBody Evaluate newEvaluate, @PathVariable("id") int id) {
	    return service.replaceEvaluate(newEvaluate, id);
	  }
	
	 @DeleteMapping("/delete/{id}")
	  void deleteEvaluate(@PathVariable int id) {
	    service.deleteEvaluate(id);
	  }
}