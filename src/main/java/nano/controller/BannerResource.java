package nano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nano.entity.Banner;
import nano.exception.ResourceNotFoundException;
import nano.service.BannerService;

@RestController
public class BannerResource {

	@Autowired
	private BannerService service;

	@GetMapping("/banner")
	public List<Banner> all() {
		return service.all();
	}

	@PostMapping("/admin/banner")
	public Integer newBanner(@RequestParam("file") MultipartFile file) {
		HttpStatus status;
		try {
			service.newBanner(file);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return status.value();
	}

	@GetMapping("/{id}")
	public Banner one(@PathVariable("id") int id) throws ResourceNotFoundException{
		return service.one(id);
	}

	@DeleteMapping("/admin/banner/{id}")
	public Integer deleteBanner(@PathVariable int id) {
		HttpStatus status;
		try {
			service.deleteBanner(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return status.value();
	}
}