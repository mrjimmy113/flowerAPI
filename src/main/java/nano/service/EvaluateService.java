package nano.service;

import java.util.List;

import nano.entity.Evaluate;

public interface EvaluateService {

	List<Evaluate> all();

	Evaluate newEvaluate(Evaluate newEvaluate);

	Evaluate one(int id);

	Evaluate replaceEvaluate(Evaluate newEvaluate, int id);

	void deleteEvaluate(int id);

}
