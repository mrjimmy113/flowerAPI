package nano.service;

import java.util.List;

import nano.dto.GetAllDTO;
import nano.dto.ItemDTO;
import nano.entity.Item;

public interface ItemService {
	GetAllDTO<ItemDTO> findAllItem(String searchTerm);
	void addItem (Item item);
	void modifyItem (Item item);
	void removeItem (Integer id);
	List<ItemDTO> searchByNamePage(String name,int pageNum);
	List<ItemDTO> getAll();
}
