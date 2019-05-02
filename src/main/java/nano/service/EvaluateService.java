package nano.service;

import java.util.List;

import nano.entity.Evaluate;
import nano.exception.ResourceNotFoundException;

public interface EvaluateService {

	List<Evaluate> all();

	Evaluate newEvaluate(Evaluate newEvaluate);

	Evaluate one(int id) throws ResourceNotFoundException;

	Evaluate replaceEvaluate(Evaluate newEvaluate, int id);

	void deleteEvaluate(int id);

}
