package nano.dto;

import java.util.Date;
import java.util.List;

import nano.entity.ItemImport;

public class ItemImportDTO {
	private Integer id;
	private Date date;
	private float total;
	private List<ItemImportDetailDTO> details;
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
	public List<ItemImportDetailDTO> getDetails() {
		return details;
	}
	public void setDetails(List<ItemImportDetailDTO> details) {
		this.details = details;
	}
	
	public ItemImport toEntity() {
		ItemImport fi = new ItemImport();
		fi.setId(this.id);
		fi.setDate(this.date);
		fi.setTotal(this.total);
		return fi;
	}
	
	
}
