package nano.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nano.dto.GetAllDTO;
import nano.entity.Account;
import nano.entity.Flower;
import nano.entity.Item;
import nano.entity.Order;
import nano.entity.OrderDetail;
import nano.entity.ProductFlower;
import nano.entity.ProductItem;
import nano.repository.AccountRepository;
import nano.repository.FlowerRepository;
import nano.repository.ItemRepository;
import nano.repository.OrderDetailRepository;
import nano.repository.OrderRepository;
import nano.utils.HelperSendEmail;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final int PAGEMAXSIZE = 9;

	private OrderRepository orderRepository;
	
	@Autowired
	AccountRepository accRep;
	
	@Autowired
	FlowerRepository fRep;
	
	@Autowired
	ItemRepository iRep;
	
	@Autowired
	OrderDetailRepository dRep;
	
	@Autowired
	HelperSendEmail helper;
	

	
	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}
	
	@Transactional
	@Override
	public List<Order> searchByNamePage(Long from,Long to, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum -1, PAGEMAXSIZE);
		Page<Order> page = orderRepository.findByOrderDateBetween(new Date(from), new Date(to), pageable);
		List<Order> orders = page.getContent();
		for (Order order : orders) {
			Account acc = accRep.findByOrders(order);
			acc.setOrders(null);
			List<OrderDetail> details = dRep.findByOrder(order);
			for (OrderDetail d : details) {
				d.setOrder(null);
			}
			order.setDetail(details);
			order.setAccount(acc);	
		}
		return page.getContent();
	}
	@Transactional
	@Override
	public GetAllDTO<Order> findAllItem(Long from,Long to) {
		Pageable pageable = PageRequest.of(0, PAGEMAXSIZE);
		Page<Order> page = orderRepository.findByOrderDateBetween(new Date(from), new Date(to), pageable);
		GetAllDTO<Order> dto = new GetAllDTO<Order>();
		dto.setMaxPage(page.getTotalPages());
		List<Order> orders = page.getContent();
		for (Order order : orders) {
			Account acc = accRep.findByOrders(order);
			acc.setOrders(null);
			List<OrderDetail> details = dRep.findByOrder(order);
			for (OrderDetail d : details) {
				d.setOrder(null);
			}
			order.setDetail(details);
			order.setAccount(acc);
		}
		dto.setList(orders);
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
	public void checkOut(Order order) throws Exception { 
		Order orders = new Order();
		Account account = accRep.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		for(OrderDetail orderDetail : order.getDetail()) {
			orderDetail.setOrder(orders);
			for (ProductFlower pf : orderDetail.getProduct().getFlowers()) {
				Flower f = fRep.findById(pf.getFlower().getId()).get();
				if(f != null) {
					int newQuantity = f.getQuantity() - orderDetail.getQuantity() * pf.getQuantity();
					if(newQuantity <0) throw new Exception("stock");
					f.setQuantity(newQuantity);
					fRep.save(f);
				}
			}
			for (ProductItem pi : orderDetail.getProduct().getItems()) {
				Item i = iRep.findById(pi.getItem().getId()).get();
				if(i != null) {
					int newQuantity = i.getQuantity() - orderDetail.getQuantity() * pi.getQuantity();
					if(newQuantity <0) throw new Exception("stock");
					i.setQuantity(newQuantity);
					iRep.save(i);
				}
			}
		}
		orders.setAccount(account);
		orders.setOrderDate(new Date());
		orders.setOrderNo((new Date()).getTime());
		orders.setOrderStatus("PROCESSING");
		orders.setPaymentType(order.getPaymentType());
		orders.setShippedDate(order.getShippedDate());
		orderRepository.save(orders);
		helper.sendEmailOrder(account.getEmail(), orders.getOrderNo(), order.getOrderStatus()); 
	}
	
	
	
	
	@Override
	public void completeOrder(int id) {
		System.out.println(helper);
		Order tmp = orderRepository.findById(id).get();
		tmp.setOrderStatus("COMPLETE");
		Account acc =accRep.findByOrderId(tmp.getOrderId()); 
		orderRepository.save(tmp);
		helper.sendEmailOrder(acc.getEmail(), tmp.getOrderNo(), tmp.getOrderStatus());
	}
	
	@Override
	public void cancelOrder(int id) {
		Order tmp = orderRepository.findById(id).get();
		tmp.setOrderStatus("CANCEL");
		Account acc =accRep.findByOrderId(tmp.getOrderId()); 
		orderRepository.save(tmp);
		helper.sendEmailOrder(acc.getEmail(), tmp.getOrderNo(), tmp.getOrderStatus());
	}
	
	


}
