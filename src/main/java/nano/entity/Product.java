package nano.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private int productId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_description")
	private String productDescription;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column()
	private String imageName;
	
	@Column(name="price")
	private float price;
	
	@Column(name="created")	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created;
	
	@ManyToOne()
	@JoinColumn(name="eventId")
	private Event event;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<ProductFlower> flowers = new ArrayList<ProductFlower>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<ProductItem> items = new ArrayList<ProductItem>();
	
	public Product() {}

	public int getProductId() {
		return productId;
	}
	
	

	public List<ProductFlower> getFlowers() {
		return flowers;
	}

	public void setFlowers(List<ProductFlower> flowers) {
		this.flowers = flowers;
	}

	public List<ProductItem> getItems() {
		return items;
	}

	public void setItems(List<ProductItem> items) {
		this.items = items;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}



	

	
	
	

	
	
}
	