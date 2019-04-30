/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nano.dao;

import java.util.List;

import nano.entity.Item;

/**
 *
 * @author LENOVO
 */
public interface ItemDAO {

    void create(Item item);

    void update(Item item);

    void delete(int id);

    Item findOne(int id);

    List<Item> findAll();
}
