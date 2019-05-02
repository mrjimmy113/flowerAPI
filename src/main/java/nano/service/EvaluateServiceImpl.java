package nano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nano.entity.Evaluate;
import nano.repository.EvaluateRepository;

@Service
public class EvaluateServiceImpl implements EvaluateService {

	@Autowired
	private EvaluateRepository repository;
	
	@Transactional
	@Override
	public List<Evaluate> all() {
		return repository.findAll();
	}

	@Transactional
	@Override
	public Evaluate newEvaluate(Evaluate newEvaluate) {
		return repository.save(newEvaluate);
	}

	@Transactional
	@Override
	public Evaluate one(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Did not find Evaluate id - " + id));
	}

	@Transactional
	@Override
	public Evaluate replaceEvaluate(Evaluate newEvaluate, int id) {
		return repository.findById(id)
			      .map(evaluate -> {
			        return repository.save(newEvaluate);
			      })
			      .orElseGet(() -> {
			        newEvaluate.setEvaluateId(id);
			        return repository.save(newEvaluate);
			      });
	}

	@Transactional
	@Override
	public void deleteEvaluate(int id) {
		repository.deleteById(id);
	}

}
