package nano.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nano.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{

}
