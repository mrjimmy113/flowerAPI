package nano.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nano.entity.Order;
import nano.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	
	@Autowired
	public void setProductRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order findById(int id) {
		Optional<Order> result = orderRepository.findById(id);		
		Order order = null;		
		if (result.isPresent()) {
			order = result.get();
		}
		else {
			throw new RuntimeException("Did not find employee id - " + id);
		}
		
		return order;
	}

	@Override
	public void save(Order product) {
		orderRepository.save(product);
	}

	@Override
	public void deleteById(int id) {
		orderRepository.deleteById(id);;
	}

}
