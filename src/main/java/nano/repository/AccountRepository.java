package nano.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nano.entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByUsernameAndPassword(String username, String password);
	
	Account findByUsername(String username);
	
	Page<Account> findByUsernameContaining(String username, Pageable pageable);
}
