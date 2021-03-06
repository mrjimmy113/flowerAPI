package nano.service;

import java.util.List;

import nano.dto.GetAllDTO;
import nano.entity.Order;
import nano.entity.OrderDetail;

public interface OrderService  {
	
	 List<Order> findAll();	
	 Order findById(int id);	
	 void save(Order order);	
	 void deleteById(int id);
	List<Order> searchByNamePage(Long from, Long to, int pageNum);
	GetAllDTO<Order> findAllItem(Long from, Long to);
	void checkOut(Order order) throws Exception;
	void completeOrder(int id);
	void cancelOrder(int id);
	List<OrderDetail> getOrderDetaild(int id);
}
