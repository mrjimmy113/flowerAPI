package nano.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import nano.entity.FlowerImport;

public interface FlowerImportRepository extends JpaRepository<FlowerImport, Integer> {
	Page<FlowerImport> findByDateBetween(Date from, Date to, Pageable pageable);
}
