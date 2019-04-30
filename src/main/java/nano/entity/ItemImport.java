package nano.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import nano.dto.ItemImportDTO;
import nano.dto.ItemImportDetailDTO;

@Entity
@Table(name = "itemImport")
public class ItemImport {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Date date;
	
	@Column
	private float total;
	
	@OneToMany(mappedBy = "itemImport")
	private List<ItemImportDetail> details;

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

	public List<ItemImportDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ItemImportDetail> details) {
		this.details = details;
	}
	
	public ItemImportDTO toDTO() {
		ItemImportDTO dto = new ItemImportDTO();
		dto.setDate(date);
		dto.setId(id);
		dto.setTotal(total);
		List<ItemImportDetailDTO> dtos = new ArrayList<ItemImportDetailDTO>();
		for (ItemImportDetail i : this.details) {
			dtos.add(i.toDTO());
		}
		dto.setDetails(dtos);
		return dto;
	}


	
	
	
}
