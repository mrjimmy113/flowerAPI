package nano.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import nano.dto.ItemImportDetailDTO;

@Entity
@Table(name = "itemimportdetail")
public class ItemImportDetail {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "itemId")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "importId")
	private ItemImport itemImport;
	
	
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemImport getItemImport() {
		return itemImport;
	}

	public void setItemImport(ItemImport itemImport) {
		this.itemImport = itemImport;
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
	
	public ItemImportDetailDTO toDTO() {
		ItemImportDetailDTO dto = new ItemImportDetailDTO();
		dto.setId(id);
		dto.setItem(item);
		dto.setQuantity(quantity);
		dto.setUnitPrice(unitPrice);
		return dto;
	}
	
	
}
