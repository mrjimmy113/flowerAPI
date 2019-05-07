package nano.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PreRemove;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductItem {
	
	@EmbeddedId
	private ProductItemId id;
	
	@JsonIgnore
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name ="productId", insertable = false, updatable = false)
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("itemId")
	@JoinColumn(name="itemId", insertable = false, updatable = false)
	private Item item;
	
	@Column
	private Integer quantity;
	
	

	public ProductItemId getId() {
		return id;
	}

	public void setId(ProductItemId id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@PreRemove
	public void preRemove() {
		product.setPrice(0);
	}
	
	
	
}
