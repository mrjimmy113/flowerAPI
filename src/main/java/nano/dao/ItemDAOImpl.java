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

import nano.entity.Item;

/**
 *
 * @author LENOVO
 */
@Repository
public class ItemDAOImpl implements ItemDAO{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Item item) {
        em.persist(item);
    }

    @Override
    public void update(Item item) {
        em.merge(item);
    }

    @Override
    public void delete(int id) {
        em.remove(findOne(id));
    }

    @Override
    public Item findOne(int id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select o from Item o").getResultList();
    }
    
}
