package nano.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nano.dto.GetAllDTO;
import nano.dto.ItemImportDTO;
import nano.dto.ItemImportDetailDTO;
import nano.entity.Item;
import nano.entity.ItemImport;
import nano.repository.ItemImportDetailRepository;
import nano.repository.ItemImportRepository;
import nano.repository.ItemRepository;

@Service
public class ItemImportServiceImpl implements ItemImportService {

	private static final int MAXPAGESIZE = 9;

	@Autowired
	ItemImportRepository importRep;

	@Autowired
	ItemImportDetailRepository detailRep;

	@Autowired
	ItemRepository itemRep;

	@Transactional
	@Override
	public void newImport(ItemImportDTO dto) {
		ItemImport ii = importRep.save(dto.toEntity());
		for (ItemImportDetailDTO detail : dto.getDetails()) {
			detailRep.save(detail.toEntity(ii));
			Item i = itemRep.findById(detail.getItem().getId()).orElse(null);
			i.setQuantity(i.getQuantity() + detail.getQuantity());
			itemRep.save(i);
		}

	}

	@Transactional(readOnly = true)
	@Override
	public GetAllDTO<ItemImportDTO> search(Long from, Long to) {
		GetAllDTO<ItemImportDTO> dto = new GetAllDTO<ItemImportDTO>();
		Pageable pageable = PageRequest.of(0, MAXPAGESIZE);
		Page<ItemImport> page = importRep.findByDateBetween(new Date(from), new Date(to), pageable);
		dto.setMaxPage(page.getTotalPages());
		List<ItemImportDTO> dtos = new ArrayList<ItemImportDTO>();
		for (ItemImport f : page.getContent()) {
			dtos.add(f.toDTO());
		}
		dto.setList(dtos);
		return dto;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ItemImportDTO> getPage(Long from, Long to, int numPage) {
		Pageable pageable = PageRequest.of(numPage, MAXPAGESIZE);
		Page<ItemImport> page = importRep.findByDateBetween(new Date(from), new Date(to), pageable);
		List<ItemImportDTO> dtos = new ArrayList<ItemImportDTO>();
		for (ItemImport f : page.getContent()) {
			dtos.add(f.toDTO());
		}
		return dtos;
	}

}
