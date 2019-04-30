package nano.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import nano.entity.ItemImport;

public interface ItemImportRepository extends JpaRepository<ItemImport, Integer> {
	Page<ItemImport> findByDateBetween(Date from, Date to, Pageable pageable);
}
