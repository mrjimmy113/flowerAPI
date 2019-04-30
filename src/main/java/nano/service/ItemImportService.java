package nano.service;

import java.util.List;

import nano.dto.GetAllDTO;
import nano.dto.ItemImportDTO;

public interface ItemImportService {
	void newImport(ItemImportDTO dto);
	GetAllDTO<ItemImportDTO> search(Long from,Long to);
	List<ItemImportDTO> getPage(Long from, Long to, int numPage);
}
