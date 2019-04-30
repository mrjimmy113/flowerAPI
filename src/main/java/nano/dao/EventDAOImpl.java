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

import nano.entity.Event;

/**
 *
 * @author LENOVO
 */
@Repository
public class EventDAOImpl implements EventDAO{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Event event) {
        em.persist(event);
    }

    @Override
    public void update(Event event) {
        em.merge(event);
    }

    @Override
    public void delete(int id) {
        em.remove(findOne(id));
    }

    @Override
    public Event findOne(int id) {
        return em.find(Event.class, id);
    }

    @Override
    public List<Event> findAll() {
        return em.createQuery("select o from Event o").getResultList();
    }
    
}
