package nano.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nano.entity.ProductItem;
import nano.entity.ProductItemId;

public interface ProductItemRepository extends JpaRepository<ProductItem, ProductItemId> {
	List<ProductItem> findByIdProductId(Integer id);
	void deleteByIdProductId(Integer id);
}
