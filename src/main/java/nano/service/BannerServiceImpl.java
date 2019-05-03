package nano.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import nano.entity.Banner;
import nano.exception.ResourceNotFoundException;
import nano.ggModules.GoogleStorageModule;
import nano.repository.BannerRepository;

@Service
public class BannerServiceImpl implements BannerService{

	@Autowired
	private BannerRepository repository;
	
	@Transactional
	@Override
	public List<Banner> all() {
		return repository.findAll();
	}

	@Transactional
	@Override
	public void newBanner(MultipartFile file) throws IOException {
		Banner banner = new Banner();
		String fileName = "Banner" + (new Date()).getTime();
		banner.setBannerName(fileName);
		banner.setImageUrl(GoogleStorageModule.upload(fileName, file.getBytes(), file.getContentType()));
		repository.save(banner);
	}

	@Transactional
	@Override
	public Banner one(int id) throws ResourceNotFoundException{
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	@Override
	public void deleteBanner(int id) throws IOException {
		Banner banner = repository.findById(id).get();
		GoogleStorageModule.delete(banner.getBannerName());
		repository.deleteById(id);
	}

    
}
