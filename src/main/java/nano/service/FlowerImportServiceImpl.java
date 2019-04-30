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

import nano.dto.FlowerImportDTO;
import nano.dto.FlowerImportDetailDTO;
import nano.dto.GetAllDTO;
import nano.entity.Flower;
import nano.entity.FlowerImport;
import nano.repository.FlowerImportDetailRepository;
import nano.repository.FlowerImportRepository;
import nano.repository.FlowerRepository;

@Service
public class FlowerImportServiceImpl implements FlowerImportService {

	private static final int MAXPAGESIZE = 9;

	@Autowired
	FlowerImportRepository importRep;

	@Autowired
	FlowerImportDetailRepository detailRep;

	@Autowired
	FlowerRepository flowerRep;

	@Transactional
	@Override
	public void newImport(FlowerImportDTO flowerImport) {
		FlowerImport fi = importRep.saveAndFlush(flowerImport.toEntity());
		for (FlowerImportDetailDTO dto : flowerImport.getDetails()) {
			detailRep.save(dto.toEntity(fi));
			Flower f = flowerRep.findById(dto.getFlower().getId()).orElse(null);
			f.setQuantity(f.getQuantity() + dto.getQuantity());
			flowerRep.save(f);
		}

	}

	@Transactional(readOnly = true)
	@Override
	public GetAllDTO<FlowerImportDTO> search(Long from, Long to) {
		GetAllDTO<FlowerImportDTO> dto = new GetAllDTO<FlowerImportDTO>();
		Pageable pageable = PageRequest.of(0, MAXPAGESIZE);
		System.out.println((new Date(from).toString()));
		Page<FlowerImport> page = importRep.findByDateBetween(new Date(from), new Date(to), pageable);
		dto.setMaxPage(page.getTotalPages());
		List<FlowerImportDTO> dtos = new ArrayList<FlowerImportDTO>();
		for (FlowerImport f : page.getContent()) {
			dtos.add(f.toDTO());
		}
		dto.setList(dtos);
		return dto;
	}

	@Transactional(readOnly = true)
	@Override
	public List<FlowerImportDTO> getPage(Long from, Long to, int numPage) {
		Pageable pageable = PageRequest.of(numPage, MAXPAGESIZE);
		Page<FlowerImport> page = importRep.findByDateBetween(new Date(from), new Date(to), pageable);
		List<FlowerImportDTO> dtos = new ArrayList<FlowerImportDTO>();
		for (FlowerImport f : page.getContent()) {
			dtos.add(f.toDTO());
		}
		return dtos;
	}

}
