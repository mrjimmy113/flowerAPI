package nano.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostRemove;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import nano.dto.ItemDTO;

/* 
Created By Nguyen Viet Minh Quang
 */
@Entity
@Table(name = "item")
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	@NotBlank(message = "Please fill in Item name")
	private String name;
	@Column
	@NotNull(message = "Please fill in Price")
	@Min(value = 0, message = "Price must be positive")
	private float price;
	@Column
	@NotNull(message = "Please fill in Quantity")
	@Min(value = 0, message = "Quantity must be positive")
	private int quantity;
	
	@JsonIgnore
	@OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
	private List<ProductItem> products = new ArrayList<ProductItem>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "item")
	private List<ItemImportDetail> imports;

	


	public List<ProductItem> getProducts() {
		return products;
	}

	public void setProducts(List<ProductItem> products) {
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ItemImportDetail> getImports() {
		return imports;
	}

	public void setImports(List<ItemImportDetail> imports) {
		this.imports = imports;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + this.id;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Item other = (Item) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

	public ItemDTO toDTO() {
		ItemDTO dto = new ItemDTO();
		dto.setId(this.id);
		dto.setName(this.name);
		dto.setStock(this.quantity);
		dto.setPrice(this.price);
		return dto;
	}
	
	@PostRemove
	public void postRemove() {
		imports.forEach(i -> {
			i.setItem(null);
		});
	}

}
