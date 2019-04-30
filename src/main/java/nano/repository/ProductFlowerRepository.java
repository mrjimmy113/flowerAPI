package nano.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nano.entity.ProductFlower;
import nano.entity.ProductFlowerId;

public interface ProductFlowerRepository extends JpaRepository<ProductFlower, ProductFlowerId> {
	List<ProductFlower> findByIdProductId(Integer id);
	void deleteByIdProductId(Integer id);
}
