package nano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nano.entity.Evaluate;

/**
 * @author Le Ngo Minh <br/>
 *
 * Modified Date : Apr 25, 2019
 */

@Repository("evaluateRepository")
public interface EvaluateRepository extends JpaRepository<Evaluate, Integer> {

}
