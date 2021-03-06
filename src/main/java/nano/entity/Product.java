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
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import nano.dto.ProductDTO;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_description", columnDefinition = "LONGTEXT")
	private String productDescription;

	@Column(name = "image_url")
	private String imageUrl;

	@Column()
	private String imageName;

	@Column(name = "price")
	private float price;

	@Column(name = "created")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created;

	@ManyToOne()
	@JoinColumn(name = "eventId")
	private Event event;

	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<ProductFlower> flowers = new ArrayList<ProductFlower>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<ProductItem> items = new ArrayList<ProductItem>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Evaluate> evaluates = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> details = new ArrayList<OrderDetail>();

	public Product() {
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}
	
	

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", imageUrl=" + imageUrl + ", imageName=" + imageName + ", price=" + price
				+ ", created=" + created + ", event=" + event + ", flowers=" + flowers + ", items=" + items
				+ ", evaluates=" + evaluates + "]";
	}

	public ProductDTO toDTO() {
		ProductDTO dto = new ProductDTO();
		dto.setCreated(created);
		dto.setEvaluates(evaluates);
		dto.setEvent(event);
		dto.setImageName(imageName);
		dto.setImageUrl(imageUrl);
		dto.setPrice(price);
		dto.setProductDescription(productDescription);
		dto.setProductId(productId);
		dto.setProductName(productName);
		dto.setFlowers(null);
		dto.setItems(null);
		dto.setEvaluates(null);
		return dto;
	}
	
	@PreRemove
	public void preRemove() {
		details.forEach(d -> {
			d.setProduct(null);
		});
	}

}
