package nano.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import nano.entity.Banner;
import nano.exception.ResourceNotFoundException;

public interface BannerService {

	List<Banner> all();

	Banner newBanner(Banner newBanner, BindingResult result);

	Banner one(int id) throws ResourceNotFoundException;

	void deleteBanner(int id);

}
