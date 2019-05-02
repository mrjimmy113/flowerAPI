package nano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nano.entity.Banner;

/**
 * @author Le Ngo Minh <br/>
 *
 * Modified Date : Apr 25, 2019
 */

@Repository("bannerRepository")
public interface BannerRepository extends JpaRepository<Banner, Integer> {

}
