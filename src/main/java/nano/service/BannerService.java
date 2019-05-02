package nano.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import nano.entity.Banner;

public interface BannerService {

	List<Banner> all();

	Banner newBanner(Banner newBanner, BindingResult result);

	Banner one(int id);

	void deleteBanner(int id);

}
