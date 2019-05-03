/*
s * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nano.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import nano.dto.GetAllDTO;
import nano.dto.ProductDTO;
import nano.entity.Product;
import nano.entity.ProductFlower;
import nano.entity.ProductFlowerId;
import nano.entity.ProductItem;
import nano.entity.ProductItemId;
import nano.ggModules.GoogleStorageModule;
import nano.repository.ProductFlowerRepository;
import nano.repository.ProductItemRepository;
import nano.repository.ProductRepository;

/**
 *
 * @author LENOVO
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	private static final int PAGEMAXSIZE = 9;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductFlowerRepository pfRep;
	
	@Autowired
	private ProductItemRepository piRep;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(int id) {
		Optional<Product> result = productRepository.findById(id);
		Product product = null;
		if (result.isPresent()) {
			product = result.get();
		} else {
			throw new RuntimeException("Did not find employee id - " + id);
		}

		return product;
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void deleteById(int id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public void addProduct(MultipartFile file, String json) throws IOException {
		Gson gson = new Gson();
		Product product = gson.fromJson(json, Product.class);
		String fileName = product.getProductName().replaceAll("\\s+", "").toLowerCase()+ product.getCreated().getTime();
		product.setImageUrl(GoogleStorageModule.upload( fileName,file.getBytes(), file.getContentType()));
		product.setImageName(fileName);
		Product inserted = productRepository.saveAndFlush(product);
		for (ProductFlower pf : product.getFlowers()) {
			pf.setProduct(inserted);
			pf.setId(new ProductFlowerId(inserted.getProductId(), pf.getFlower().getId()));
			pfRep.save(pf);
		}
		for (ProductItem pi : product.getItems()) {
			pi.setProduct(inserted);
			pi.setId(new ProductItemId(inserted.getProductId(), pi.getItem().getId()));
			piRep.save(pi);
		}
	}
	
	@Transactional
	@Override
	public List<ProductDTO> searchByNamePage(String name, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum -1, PAGEMAXSIZE);
		Page<Product> page = productRepository.findByProductNameContaining(name, pageable);
		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
		for (Product product : page.getContent()) {
			dtos.add(product.toDTO());
		}
		return dtos;
	}
	@Transactional
	@Override
	public GetAllDTO<ProductDTO> findAllItem(String searchTerm) {
		Pageable pageable = PageRequest.of(0, PAGEMAXSIZE);
		Page<Product> page = productRepository.findByProductNameContaining(searchTerm, pageable);
		GetAllDTO<ProductDTO> dto = new GetAllDTO<ProductDTO>();
		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
		for (Product product : page.getContent()) {
			dtos.add(product.toDTO());
		}
		dto.setMaxPage(page.getTotalPages());
		dto.setList(dtos);
		return dto;
	}

	@Override
	public List<ProductFlower> getFlowers(Integer id) {
		return pfRep.findByIdProductId(id);
	}

	@Override
	public List<ProductItem> getItems(Integer id) {
		// TODO Auto-generated method stub
		return piRep.findByIdProductId(id);
	}
	
	@Transactional
	@Override
	public void updateProduct(MultipartFile file, String json) throws IOException  {
		Gson gson = new Gson();
		Product product = gson.fromJson(json, Product.class);
		if(file != null) {
			GoogleStorageModule.delete(product.getImageName());
			String fileName = product.getProductName().replaceAll("\\s+", "").toLowerCase()+ product.getCreated().getTime();
			product.setImageUrl(GoogleStorageModule.upload( fileName,file.getBytes(), file.getContentType()));
			product.setImageName(fileName);
		}
		Product inserted = productRepository.saveAndFlush(product);
		pfRep.deleteByIdProductId(inserted.getProductId());
		piRep.deleteByIdProductId(inserted.getProductId());
		for (ProductFlower pf : product.getFlowers()) {
			pf.setProduct(inserted);
			pf.setId(new ProductFlowerId(inserted.getProductId(), pf.getFlower().getId()));
			pfRep.save(pf);
		}
		for (ProductItem pi : product.getItems()) {
			pi.setProduct(inserted);
			pi.setId(new ProductItemId(inserted.getProductId(), pi.getItem().getId()));
			piRep.save(pi);
		}
	}

	@Override
	public Product getDetail(Integer id) {
		Product product = productRepository.findById(id).get();
		product.setFlowers(pfRep.findByIdProductId(id));
		product.setItems(piRep.findByIdProductId(id));
		System.out.println(product.getFlowers().size());
		return product;
	}

}
