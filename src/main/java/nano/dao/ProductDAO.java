/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nano.dao;

import java.util.List;

import nano.entity.Product;

/**
 *
 * @author LENOVO
 */
public interface ProductDAO {

    void create(Product product);

    void update(Product product);

    void delete(int id);

    Product findOne(int id);

    List<Product> findAll();
}
