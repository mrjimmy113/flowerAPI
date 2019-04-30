/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nano.dao;

import java.util.List;

import nano.entity.Event;

/**
 *
 * @author LENOVO
 */
public interface EventDAO {

    void create(Event event);

    void update(Event event);

    void delete(int id);

    Event findOne(int id);

    List<Event> findAll();
}
