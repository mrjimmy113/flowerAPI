package nano.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nano.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Page<Product> findByProductNameContaining(String searchTerm, Pageable pageable);
	
	@Query("SELECT p from Product p "
			+ "join ProductFlower f "
			+ "on p.id = f.product.id "
			+ "WHERE p.event.id = :event AND f.flower.id = :flower")
	Page<Product> findByFlowerAndEvent(@Param("event")Integer event,@Param("flower") Integer flower, Pageable pageable);
	
	@Query("SELECT p from Product p "
			+ "join ProductFlower f "
			+ "on p.id = f.product.id "
			+ "WHERE f.flower.id = :flower")
	Page<Product> findByFlower(@Param("flower") Integer flower, Pageable pageable);
	
	@Query("SELECT p from Product p "
			+ "WHERE p.event.id = :event" )
	Page<Product> findByEvent(@Param("event")Integer event, Pageable pageable);
	
	List<Product> findTop10ByOrderByCreatedDesc();
}
