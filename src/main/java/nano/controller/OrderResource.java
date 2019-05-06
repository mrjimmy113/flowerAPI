package nano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nano.dto.GetAllDTO;
import nano.entity.Order;
import nano.service.OrderService;


@RestController
public class OrderResource {
	
	OrderService orderService;
	
	
	@Autowired
	public void setorderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/orders")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public GetAllDTO<Order> getAllorder(@RequestParam Long from, @RequestParam Long to) {
		return orderService.findAllItem(from, to);
	}
	
	@GetMapping(value = "/orders/search")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public List<Order> searchWithPage(@RequestParam Long from,@RequestParam Long to, @RequestParam int pageNum) {
		List<Order> orders = null;		
		orders = orderService.searchByNamePage(from, to, pageNum);
		return orders;
	}
	
	@GetMapping("/orders/{orderId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public Order getorder(@PathVariable int orderId) {
		Order order = orderService.findById(orderId);		
		return order;
	}
	
	@PostMapping("/orders")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP','ROLE_USER')")
	public Integer addorder(@RequestBody Order order) {
		HttpStatus status = null;
		try {
			orderService.checkOut(order);
			status = HttpStatus.OK;
		} catch (Exception e) {
			if(e.getMessage().equals("stock")) status = HttpStatus.ACCEPTED;
			else status = HttpStatus.BAD_REQUEST;
		}
		return status.value();
	}
	
	@PutMapping("/orders")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
	
	@PutMapping("/orders/complete")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public ResponseEntity<Integer> completeOrder(@RequestParam("id") Integer id) {
		HttpStatus status = null;
		try {
			orderService.completeOrder(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(),status);
	}
	
	@PutMapping("/orders/cancel")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMP')")
	public ResponseEntity<Integer> cancelOrder(@RequestParam("id") Integer id) {
		HttpStatus status = null;
		try {
			orderService.cancelOrder(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Integer>(status.value(),status);
	}
	
}
