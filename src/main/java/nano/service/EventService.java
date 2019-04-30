package nano.service;

import java.util.List;

import nano.entity.Event;

public interface EventService {
	List<Event> findAllEvent();
	void addEvent(Event event);
	void modifyEvent(Event event);
	void removeEvent(Integer id);
}
