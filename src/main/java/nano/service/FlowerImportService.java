package nano.service;

import java.util.List;

import nano.dto.FlowerImportDTO;
import nano.dto.GetAllDTO;

public interface FlowerImportService {
	void newImport(FlowerImportDTO flowerImport);
	GetAllDTO<FlowerImportDTO> search(Long from,Long to);
	List<FlowerImportDTO> getPage(Long from, Long to, int numPage);
}
