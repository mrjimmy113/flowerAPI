package nano.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nano.entity.Order;
import nano.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	List<OrderDetail> findByOrder(Order order);
	
	@Query("SELECT d from OrderDetail d "
			+ "WHERE d.order.orderId = :id")
	List<OrderDetail> findByOrderId(@Param("id") Integer id);
	
	@Query("SELECT d from OrderDetail d "
			+ "left join fetch d.product "
			+ "WHERE d.order.orderId = :id")
	List<OrderDetail> findByOrderIdFetchProduct(@Param("id") Integer id);
}
