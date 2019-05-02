package nano.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import nano.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	Page<Order> findByOrderDateBetween(Date from, Date to, Pageable pageable);
}
