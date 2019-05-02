package nano.service;

import java.util.List;

import nano.entity.Order;

public interface OrderService  {
	
	 List<Order> findAll();	
	 Order findById(int id);	
	 void save(Order order);	
	 void deleteById(int id);
}
