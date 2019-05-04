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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nano.dto.GetAllDTO;
import nano.dto.ProductDTO;
import nano.entity.ProductFlower;
import nano.entity.ProductItem;
import nano.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	@Autowired
	ProductService productService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GetAllDTO<ProductDTO>> getAllProduct(@RequestParam String searchTerm) {
		HttpStatus status = null;
		GetAllDTO<ProductDTO> dto = null;
		try {
			dto = productService.findAllItem(searchTerm);
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<GetAllDTO<ProductDTO>>(dto,status);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<ProductDTO>> searchWithPage(@RequestParam String searchTerm, @RequestParam int pageNum) {
		List<ProductDTO> items = null;
		HttpStatus status = null;
		try {	
			items = productService.searchByNamePage(searchTerm, pageNum);
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<ProductDTO>>(items,status);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable int productId) {
		ProductDTO product = null;
		HttpStatus status = null;
		try {
			product = productService.getDetail(productId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<ProductDTO>(product,status);
	}

	@PostMapping
	public ResponseEntity<Integer> addProduct(@RequestParam("file") MultipartFile file, @RequestParam("product") String product) {
		HttpStatus status = null;
		try {
			productService.addProduct(file, product);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(), status);
	
	}

	@PostMapping("/update")
	public ResponseEntity<Integer> update(@RequestParam(name = "file", required = false) MultipartFile file, @RequestParam("product") String product) {
		System.out.println("Update" + file);
		HttpStatus status = null;
		try {
			productService.updateProduct(file, product);
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.println("BAD");
			e.printStackTrace();
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(), status);
	
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Integer> deleteProduct(@PathVariable int productId) {
		HttpStatus status = null;
		try {
			productService.deleteById(productId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(), status);
	}
	
	@GetMapping("/flowers/{id}")
	public ResponseEntity<List<ProductFlower>> getFlowers(@PathVariable int id) {
		HttpStatus status = null;
		List<ProductFlower> res = null;
		try {
			res = productService.getFlowers(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<ProductFlower>>(res,status);
	}
	
	@GetMapping("/items/{id}")
	public ResponseEntity<List<ProductItem>> getItems(@PathVariable int id) {
		HttpStatus status = null;
		List<ProductItem> res = null;
		try {
			res = productService.getItems(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<ProductItem>>(res,status);
	}
}
