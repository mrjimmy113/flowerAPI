package nano.dto;

import java.util.Date;

import nano.entity.Flower;
import nano.entity.FlowerImport;
import nano.entity.FlowerImportDetail;

public class FlowerImportDetailDTO {
	private Integer id;
	private Flower flower;
	private Date rotDate;
	private float unitPrice;
	private int quantity;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public FlowerImportDetail toEntity(FlowerImport fi) {
		FlowerImportDetail fid = new FlowerImportDetail();
		fid.setFlower(this.flower);
		fid.setFlowerImport(fi);
		fid.setId(this.id);
		fid.setQuantity(this.quantity);
		fid.setRotDate(this.rotDate);
		fid.setUnitPrice(this.unitPrice);
		
		return fid;
	}
	
	
}
