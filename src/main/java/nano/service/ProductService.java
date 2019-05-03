/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nano.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import nano.dto.GetAllDTO;
import nano.dto.ProductDTO;
import nano.entity.Product;
import nano.entity.ProductFlower;
import nano.entity.ProductItem;

/**
 *
 * @author LENOVO
 */
public interface ProductService {
	List<Product> findAll();

	Product findById(int id);

	void save(Product product);

	void deleteById(int id);

	void addProduct(MultipartFile file, String json) throws IOException;

	List<ProductDTO> searchByNamePage(String name, int pageNum);

	GetAllDTO<ProductDTO> findAllItem(String searchTerm);

	List<ProductFlower> getFlowers(Integer id);

	List<ProductItem> getItems(Integer id);

	void updateProduct(MultipartFile file, String json) throws IOException;
	
	Product getDetail(Integer id);
}
