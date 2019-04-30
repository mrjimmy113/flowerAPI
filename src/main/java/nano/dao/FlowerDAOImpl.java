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

import nano.entity.Flower;

/**
 *
 * @author LENOVO
 */
@Repository
public class FlowerDAOImpl implements FlowerDAO{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Flower flower) {
        em.persist(flower);
    }

    @Override
    public void update(Flower flower) {
        em.merge(flower);
    }

    @Override
    public void delete(int id) {
        em.remove(findOne(id));
    }

    @Override
    public Flower findOne(int id) {
        return em.find(Flower.class, id);
    }

    @Override
    public List<Flower> findAll() {
        return em.createQuery("select o from Flower o").getResultList();
    }
    
}
