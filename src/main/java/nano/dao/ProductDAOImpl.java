/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nano.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import nano.entity.Product;

/**
 *
 * @author LENOVO
 */
@Repository
public class ProductDAOImpl implements ProductDAO{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Product product) {
        em.persist(product);
    }

    @Override
    public void update(Product product) {
        em.merge(product);
    }

    @Override
    public void delete(int id) {
        em.remove(findOne(id));
    }

    @Override
    public Product findOne(int id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("select o from Product o").getResultList();
    }
    
}
