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

import nano.dto.FlowerImportDTO;
import nano.dto.FlowerImportDetailDTO;

@Entity
@Table(name = "flowerimport")
public class FlowerImport {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Date date;
	
	@Column
	private float total;
	
	@OneToMany(mappedBy = "flowerImport")
	private List<FlowerImportDetail> details;

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

	public List<FlowerImportDetail> getDetails() {
		return details;
	}

	public void setDetails(List<FlowerImportDetail> details) {
		this.details = details;
	}
	
	public FlowerImportDTO toDTO() {
		FlowerImportDTO dto = new FlowerImportDTO();
		dto.setId(id);
		dto.setDate(date);
		dto.setTotal(total);
		List<FlowerImportDetailDTO> dtos = new ArrayList<FlowerImportDetailDTO>();
		for (FlowerImportDetail f : this.details) {
			dtos.add(f.toDTO());
		}
		dto.setDetails(dtos);
		return dto;
	}


	
	
	
}
