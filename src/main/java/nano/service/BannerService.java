package nano.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import nano.entity.Banner;
import nano.exception.ResourceNotFoundException;

public interface BannerService {

	List<Banner> all();

	Banner one(int id) throws ResourceNotFoundException;

	void deleteBanner(int id) throws IOException;

	void newBanner(MultipartFile file) throws IOException;

}
