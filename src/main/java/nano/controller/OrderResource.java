package nano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import nano.entity.Order;
import nano.entity.Product;
import nano.service.OrderService;


@RestController
@RequestMapping("/orders")
public class OrderResource {
	
	OrderService orderService;
	
	@Autowired
	public void setorderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/orders")
	public List<Order> getAllorder() {
		return orderService.findAll();
	}
	
	@GetMapping(value = "/search")
	public List<Order> searchWithPage(@RequestParam String searchTerm, @RequestParam int pageNum) {
		List<Order> orders = null;		
		orders = orderService.searchByNamePage(searchTerm, pageNum);
		return orders;
	}
	
	@GetMapping("/orders/{orderId}")
	public Order getorder(@PathVariable int orderId) {
		Order order = orderService.findById(orderId);		
		return order;
	}
	
	@PostMapping("/orders")
	public Order addorder(@RequestBody Order order) {
		order.setOrderId(0);
		orderService.save(order);
		return order;
	}
	
	@PutMapping("/orders")
	public ResponseEntity<Integer> updateorder(@RequestBody Order order) {
		HttpStatus status = null;
		try {
			orderService.save(order);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(), status);
	}
	
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<Integer> deleteorder(@PathVariable int orderId) {		
		HttpStatus status = null;
		try {
			orderService.deleteById(orderId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(), status);
	}
	
}
