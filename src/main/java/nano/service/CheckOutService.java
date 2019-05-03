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
		orders.setPaidDate(order.getPaidDate());
		orders.setPaymentType(order.getPaymentType());
		orders.setShipAddress(order.getShipAddress());
		orders.setShipCity(order.getShipCity());
		orders.setShipCountry(order.getShipCountry());
		orders.setShipName(order.getShipName());
		orders.setShippedDate(order.getShippedDate());
		orders.setShippingFree(order.getShippingFree());
		orderRepository.save(orders);
		
		HelperSendEmail helper = new HelperSendEmail();
		helper.sendEmailOrder("", orders.getOrderNo()); // thêm emailCustomer
	}
	
	
}
