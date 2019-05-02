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

import nano.entity.Order;
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
	public Order updateorder(@RequestBody Order order) {
		orderService.save(order);
		return order;
	}
	
	@DeleteMapping("/orders/{orderId}")
	public String deleteorder(@PathVariable int orderId) {		
		Order temporder = orderService.findById(orderId);		
		if(temporder != null) {
			orderService.deleteById(orderId);
		}
		return "Deleted order id - " + orderId;
	}
	
}
