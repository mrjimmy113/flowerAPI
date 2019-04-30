package nano.dto;

import nano.entity.Item;

public class ItemDTO {
	private Integer id;
	private String name;
	private float price;
	private int stock;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Item toItem() {
		Item item = new Item();
		item.setId(this.id);
		item.setName(this.name);
		item.setPrice(this.price);
		item.setQuantity(this.stock);
		
		return item;
	}
	
	
}
