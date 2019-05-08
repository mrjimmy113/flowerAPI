package nano.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import nano.entity.Flower;

public interface FlowerRepository extends JpaRepository<Flower, Integer>{
	Page<Flower> findByNameContaining(String name, Pageable pageable);

}
