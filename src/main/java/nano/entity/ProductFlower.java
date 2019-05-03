package nano.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductFlower {
	
	@EmbeddedId
	private ProductFlowerId id;
	
	@JsonIgnore
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name ="productId", insertable = false, updatable = false)
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("flowerId")
	@JoinColumn(name ="flowerId", insertable = false, updatable = false)
	private Flower flower;
	
	@Column
	private Integer quantity;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductFlowerId getId() {
		return id;
	}

	public void setId(ProductFlowerId id) {
		this.id = id;
	}
	
	
	
	
	
}
