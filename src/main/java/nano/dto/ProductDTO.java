package nano.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import nano.entity.Evaluate;
import nano.entity.Event;

public class ProductDTO {

	private int productId;

	private String productName;

	private String productDescription;

	private String imageUrl;

	private String imageName;

	private float price;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created;

	private Event event;

	private List<ProductFlowerDTO> flowers = new ArrayList<ProductFlowerDTO>();

	private List<ProductItemDTO> items = new ArrayList<ProductItemDTO>();

	private List<Evaluate> evaluates = new ArrayList<>();

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}


	public List<ProductFlowerDTO> getFlowers() {
		return flowers;
	}

	public void setFlowers(List<ProductFlowerDTO> flowers) {
		this.flowers = flowers;
	}

	public List<ProductItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ProductItemDTO> items) {
		this.items = items;
	}

	public List<Evaluate> getEvaluates() {
		return evaluates;
	}

	public void setEvaluates(List<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}
	

}
