package nano.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import nano.entity.Account;
import nano.entity.Order;
import nano.entity.OrderDetail;
import nano.repository.OrderRepository;
import nano.utils.HelperSendEmail;

public class CheckOutService {

	private OrderRepository orderRepository;
	
	@Autowired
	public void setProductRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}	
	
	public void checkOut(Order order, List<OrderDetail> details) { 
		Order orders = new Order();
		Account account = orders.getAccount();
		for(OrderDetail orderDetail : details) {
			orderDetail.setOrder(orders);
		}
		orders.setDetail(details);
		orders.setAccount(account);
		orders.setOrderNo(order.getOrderDate().getTime());
		orders.setOrderDate(new Date());
		orders.setOrderStatus("Processing");
		orders.setPaymentType(order.getPaymentType());
		orders.setShippedDate(order.getShippedDate());
		orderRepository.save(orders);
		
		HelperSendEmail helper = new HelperSendEmail();
		helper.sendEmailOrder(account.getEmail(), orders.getOrderNo()); // thêm emailCustomer
	}
	
	
}
