package nano.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nano.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	Page<Item> findByNameContaining(String name, Pageable pageable);
	
}
