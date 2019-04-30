package nano.dto;

import nano.entity.Event;

public class EventDTO {
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Event toEntity() {
		Event event = new Event();
		event.setId(this.id);
		event.setName(this.name);
		return event;
	}
}
