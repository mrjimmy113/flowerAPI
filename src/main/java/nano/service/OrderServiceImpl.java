package nano.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nano.dto.GetAllDTO;
import nano.entity.Order;
import nano.entity.OrderDetail;
import nano.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final int PAGEMAXSIZE = 9;

	private OrderRepository orderRepository;
	
	@Autowired
	public void setProductRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}
	
	@Transactional
	@Override
	public List<Order> searchByNamePage(Long from,Long to, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum -1, PAGEMAXSIZE);
		Page<Order> page = orderRepository.findByOrderDateBetween(new Date(from), new Date(to), pageable);		
		return page.getContent();
	}
	@Transactional
	@Override
	public GetAllDTO<Order> findAllItem(Long from,Long to) {
		Pageable pageable = PageRequest.of(0, PAGEMAXSIZE);
		Page<Order> page = orderRepository.findByOrderDateBetween(new Date(from), new Date(to), pageable);
		GetAllDTO<Order> dto = new GetAllDTO<Order>();
		dto.setMaxPage(page.getTotalPages());
		dto.setList(page.getContent());
		return dto;
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
		for (OrderDetail od : product.getDetail()) {
			od.setOrder(product);
		}
		orderRepository.save(product);
	}

	@Override
	public void deleteById(int id) {
		orderRepository.deleteById(id);;
	}


}
