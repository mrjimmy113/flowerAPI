package nano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nano.entity.Event;
import nano.repository.EventRepository;
@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository rep;
	
	@Transactional
	@Override
	public List<Event> findAllEvent() {
		return rep.findAll();
	}

//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Transactional
	@Override
	public void addEvent(Event event) {
		rep.save(event);
	}

	@Transactional
	@Override
	public void modifyEvent(Event event) {
		if(event.getId() == null) return;
		rep.save(event);
	}

//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Transactional	
	@Override
	public void removeEvent(Integer id) {
		rep.deleteById(id);
	}

}
