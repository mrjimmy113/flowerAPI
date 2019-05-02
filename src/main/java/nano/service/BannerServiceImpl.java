package nano.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import nano.entity.Banner;
import nano.exception.ResourceNotFoundException;
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
	public Banner newBanner(Banner newBanner, BindingResult result) {
		
		boolean fileError = false;

		if (!((newBanner.getBannerId() != null) && newBanner.getFile().isEmpty())) {
			if (!newBanner.getFile().getContentType().contains("image")) {
				ObjectError error = new ObjectError("file", "file");
				result.addError(error);
				fileError = true;
			}
		}

		if (result.hasErrors()) {
			if(fileError)
			return null;
		}
		if (!((newBanner.getBannerId() != null) && newBanner.getFile().isEmpty())) {
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/png;base64,");
			try {
				sb.append(Base64.getEncoder().encodeToString(newBanner.getFile().getBytes()));
				newBanner.setPhotoBase64(sb.toString());
			} catch (IOException e) {
				return null;
			}
		}				
		
		return repository.save(newBanner);
	}

	@Transactional
	@Override
	public Banner one(int id) throws ResourceNotFoundException{
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	@Override
	public void deleteBanner(int id) {
		repository.deleteById(id);
	}

    
}
