/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nano.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import nano.dto.FlowerDTO;
import nano.dto.GetAllDTO;
import nano.entity.Flower;

/**
 *
 * @author LENOVO
 */
public interface FlowerService {
    void addFlower(Flower flower);
    void remove(int id);
    Flower getById(int id);
    GetAllDTO<FlowerDTO> findAllFlower(String searchTerm);
    List<FlowerDTO> searchByNamePage(String name,int pageNum);
    List<FlowerDTO> getAll();
	void modifyFlower(MultipartFile file, String dto) throws IOException;
}
