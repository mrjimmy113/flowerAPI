package nano.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nano.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
