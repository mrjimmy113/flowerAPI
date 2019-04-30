/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nano.dao;

import java.util.List;

import nano.entity.Flower;

/**
 *
 * @author LENOVO
 */
public interface FlowerDAO {

    void create(Flower flower);

    void update(Flower flower);

    void delete(int id);

    Flower findOne(int id);

    List<Flower> findAll();
}
