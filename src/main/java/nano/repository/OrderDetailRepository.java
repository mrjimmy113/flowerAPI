package nano.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nano.entity.Order;
import nano.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	List<OrderDetail> findByOrder(Order order);
}
