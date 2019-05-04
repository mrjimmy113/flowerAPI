package nano.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nano.dto.GetAllDTO;
import nano.dto.ItemDTO;
import nano.entity.Item;
import nano.repository.ItemRepository;
@Service
public class ItemServiceImpl implements ItemService {

	private static final int PAGEMAXSIZE = 9;
	
	@Autowired
	ItemRepository rep;

	@Transactional
	@Override
	public GetAllDTO<ItemDTO> findAllItem(String searchTerm) {
		Pageable pageable = PageRequest.of(0, PAGEMAXSIZE);
		Page<Item> page = rep.findByNameContaining(searchTerm, pageable);
		GetAllDTO<ItemDTO> dto = new GetAllDTO<ItemDTO>();
		List<ItemDTO> dtos = new ArrayList<ItemDTO>();
		for (Item item : page.getContent()) {
			dtos.add(item.toDTO());
		}
		dto.setMaxPage(page.getTotalPages());
		dto.setList(dtos);
		return dto;
	}

//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Transactional
	@Override
	public void addItem(Item item) {
		rep.save(item);
	}

	@Transactional
	@Override
	public void modifyItem(Item item) {
		if(item.getId() == null) return;
		rep.save(item);
	}
	
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Transactional
	@Override
	public void removeItem(Integer id) {
		rep.deleteById(id);
	}

	@Transactional
	@Override
	public List<ItemDTO> searchByNamePage(String name, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum -1, PAGEMAXSIZE);
		Page<Item> page = rep.findByNameContaining(name, pageable);
		List<ItemDTO> dtos = new ArrayList<ItemDTO>();
		for (Item item : page.getContent()) {
			dtos.add(item.toDTO());
		}
		return dtos;
	}

	@Override
	public List<ItemDTO> getAll() {
		List<ItemDTO> dtos = new ArrayList<ItemDTO>();
		for (Item i : rep.findAll()) {
			dtos.add(i.toDTO());
		}
		return dtos;
	}

	@Override
	public ItemDTO findOne(int id) {
		return rep.findById(id).get().toDTO();
	}
	
	

	
	
}
