package nano.dto;

import java.util.Date;
import java.util.List;

import nano.entity.FlowerImport;

public class FlowerImportDTO {
	private Integer id;
	private Date date;
	private float total;
	private List<FlowerImportDetailDTO> details;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<FlowerImportDetailDTO> getDetails() {
		return details;
	}
	public void setDetails(List<FlowerImportDetailDTO> details) {
		this.details = details;
	}
	
	public FlowerImport toEntity() {
		FlowerImport fi = new FlowerImport();
		fi.setId(this.id);
		fi.setDate(this.date);
		fi.setTotal(this.total);
		
		return fi;
	}
	
	
}
