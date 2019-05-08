package nano.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import nano.dto.FlowerImportDetailDTO;

@Entity
@Table(name = "flowerimportdetail")
public class FlowerImportDetail {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flowerId")
	private Flower flower;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "importId")
	private FlowerImport flowerImport;
	
	@Column
	private Date rotDate;
	
	
	@Column
	private float unitPrice;
	
	@Column
	private Integer quantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}

	public FlowerImport getFlowerImport() {
		return flowerImport;
	}

	public void setFlowerImport(FlowerImport flowerImport) {
		this.flowerImport = flowerImport;
	}

	public Date getRotDate() {
		return rotDate;
	}

	public void setRotDate(Date rotDate) {
		this.rotDate = rotDate;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
	public FlowerImportDetailDTO toDTO() {
		FlowerImportDetailDTO dto = new FlowerImportDetailDTO();
		dto.setFlower(flower);
		dto.setId(id);
		dto.setQuantity(quantity);
		dto.setRotDate(rotDate);
		dto.setUnitPrice(unitPrice);
		return dto;
	}
	
	
}
