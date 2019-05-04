package nano.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nano.entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByUsernameAndPassword(String username, String password);
	
	Account findByUsername(String username);
	
	Page<Account> findByUsernameContaining(String username, Pageable pageable);

	@Query("select p from Account p left join fetch p.orders")
	List<Account> myFindAll();
	
	Account findByEmail(String email);
}
