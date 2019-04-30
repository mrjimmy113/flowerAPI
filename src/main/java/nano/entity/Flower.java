package nano.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import nano.dto.FlowerDTO;

/* 
 Created By Nguyen Viet Minh Quang
 */
@Entity
@Table(name = "flower")
public class Flower implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	@NotBlank(message = "Please fill in Flower name")
	private String name;
    @Column(name="content", columnDefinition = "LONGTEXT")
    private String content;
    @Column
    private String fileName;
	@Column
	@NotNull(message = "Please fill in Price")
    @Min(value = 0, message = "Price must be positive")
	private float price;
	@Column
	@NotNull(message = "Please fill in Quantity")
    @Min(value = 0, message = "Quantity must be positive")
	private int quantity;
	
	@Transient
	private MultipartFile file;

	@ManyToOne
	@JoinColumn(name = "eventId")
	private Event event;

	@JsonIgnore
	@OneToMany(mappedBy = "flower")
	private List<ProductFlower> products = new ArrayList<ProductFlower>();

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

	public List<ProductFlower> getProducts() {
		return products;
	}

	public void setProducts(List<ProductFlower> products) {
		this.products = products;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}



	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + this.id;
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
		final Flower other = (Flower) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	public FlowerDTO toDTO() {
		FlowerDTO dto = new FlowerDTO();
		dto.setFileName(fileName);
		dto.setId(id);
		dto.setImageUrl(content);
		dto.setName(name);
		dto.setPrice(price);
		dto.setQuantity(quantity);
		return dto;
	}

	


}
