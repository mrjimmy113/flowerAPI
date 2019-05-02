package nano.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import nano.entity.Order;
import nano.entity.Product;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	Page<Order> findByProductNameContaining(String searchTerm, Pageable pageable);
}
