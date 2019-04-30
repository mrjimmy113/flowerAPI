package nano.dto;

import nano.entity.Flower;

public class FlowerDTO {
	private Integer id;
	private String name;
	private String eventId;
	private String imageUrl;
	private String fileName;
	private float price;
	private Integer quantity;

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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	
	public Flower toEntity() {
		Flower entity = new Flower();
		entity.setContent(imageUrl);
		entity.setFileName(fileName);
		entity.setName(name);
		entity.setId(id);
		entity.setPrice(price);
		entity.setQuantity(quantity);	
		
		return entity;
	}
	
	
	
	
	
	
	
	
}
