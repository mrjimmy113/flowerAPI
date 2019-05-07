/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nano.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import nano.dto.FlowerDTO;
import nano.dto.GetAllDTO;
import nano.entity.Event;
import nano.entity.Flower;
import nano.ggModules.GoogleStorageModule;
import nano.repository.EventRepository;
import nano.repository.FlowerRepository;

/**
 *
 * @author LENOVO
 */
@Service
public class FlowerServiceImpl implements FlowerService {

	private static final int PAGEMAXSIZE = 9;
	
	@Autowired
	FlowerRepository rep;
	
	@Autowired
	EventRepository eRep;


	@Transactional
	@Override
	public void remove(int id) throws IOException {
		Flower tmp = rep.findById(id).get();
		if(tmp != null) {
			GoogleStorageModule.delete(tmp.getFileName());
			rep.deleteById(id);
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public Flower getById(int id) {
		return rep.findById(id).orElse(null);
	}


	@Transactional
	@Override
	public void modifyFlower(MultipartFile file, String dto) throws IOException {
		System.out.println(file);
		Gson g = new Gson();
		FlowerDTO tmp = g.fromJson(dto, FlowerDTO.class);
		Flower flower = new Flower();
		flower.setId(tmp.getId());
		flower.setName(tmp.getName());
		flower.setPrice(tmp.getPrice());
		flower.setContent(tmp.getImageUrl());
		flower.setFileName(tmp.getFileName());
		Event ev = eRep.findById(tmp.getEventId()).get();
		System.out.println("Event Name" + ev.getName());
		if(ev != null) flower.setEvent(ev);
		if(file != null) {
			String fileName = flower.getName().replaceAll("\\s+", "").toLowerCase()
					+ (new java.util.Date().getTime());
			flower.setFileName(fileName);
			GoogleStorageModule.delete(tmp.getFileName());
			flower.setContent(GoogleStorageModule.upload(fileName, file.getBytes(), file.getContentType()));
		}
		rep.save(flower);
	}


	@Transactional
	@Override
	public void addFlower(MultipartFile file, String dto) throws IOException {
		Gson g = new Gson();
		FlowerDTO tmp = g.fromJson(dto, FlowerDTO.class);
		Flower flower = new Flower();
		flower.setName(tmp.getName());
		flower.setPrice(tmp.getPrice());
		Event ev = eRep.findById(tmp.getEventId()).get();
		System.out.println("Event Name" + ev.getName());
		if(ev != null) flower.setEvent(ev);
		String fileName = flower.getName().replaceAll("\\s+", "").toLowerCase()
				+ (new java.util.Date().getTime());
		flower.setFileName(fileName);
		flower.setContent(GoogleStorageModule.upload(fileName, file.getBytes(), file.getContentType()));
		rep.save(flower);
	}

	@Transactional(readOnly = true)
	@Override
	public GetAllDTO<FlowerDTO> findAllFlower(String searchTerm) {
		Pageable pageable = PageRequest.of(0, PAGEMAXSIZE);
		Page<Flower> page = rep.findByNameContaining(searchTerm, pageable);
		GetAllDTO<FlowerDTO> dto = new GetAllDTO<FlowerDTO>();
		List<FlowerDTO> dtos = new ArrayList<FlowerDTO>();
		for (Flower item : page.getContent()) {
			FlowerDTO fDTO = item.toDTO();
			fDTO.setEventId(item.getEvent().getId());
			dtos.add(fDTO);
			
		}
		dto.setMaxPage(page.getTotalPages());
		dto.setList(dtos);
		return dto;
	}

	@Override
	public List<FlowerDTO> searchByNamePage(String name, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum -1, PAGEMAXSIZE);
		Page<Flower> page = rep.findByNameContaining(name, pageable);
		List<FlowerDTO> dtos = new ArrayList<FlowerDTO>();
		for (Flower item : page.getContent()) {
			FlowerDTO fDTO = item.toDTO();
			fDTO.setEventId(item.getEvent().getId());
			dtos.add(fDTO);
		}
		return dtos;
	}

	@Override
	public List<FlowerDTO> getAll() {
		List<FlowerDTO> dtos = new ArrayList<FlowerDTO>();
		for (Flower f : rep.findAll()) {
			dtos.add(f.toDTO());
		}
		return dtos;
	}

	@Override
	public FlowerDTO findOne(int id) {
		return rep.findById(id).get().toDTO();
	}

}
