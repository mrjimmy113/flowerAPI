package nano.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nano.dto.GetAllDTO;
import nano.entity.Account;
import nano.entity.Order;
import nano.entity.OrderDetail;
import nano.repository.AccountRepository;
import nano.repository.OrderRepository;
import nano.utils.HelperSendEmail;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final int PAGEMAXSIZE = 9;

	private OrderRepository orderRepository;
	
	@Autowired
	AccountRepository accRep;
	
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
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void checkOut(Order order) { 
		Order orders = new Order();
		Account account = accRep.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(account.getUsername());
		for(OrderDetail orderDetail : order.getDetail()) {
			orderDetail.setOrder(orders);
		}
		orders.setAccount(account);
		orders.setOrderDate(new Date());
		orders.setOrderNo((new Date()).getTime());
		orders.setOrderStatus("Processing");
		orders.setPaymentType(order.getPaymentType());
		orders.setShippedDate(order.getShippedDate());
		System.out.println("set order");
		orderRepository.save(orders);
		System.out.println("Send email");
		HelperSendEmail helper = new HelperSendEmail();
		System.out.println(account.getEmail());
		helper.sendEmailOrder(account.getEmail(), orders.getOrderNo()); // thêm emailCustomer
	}


}
