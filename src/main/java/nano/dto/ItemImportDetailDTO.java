package nano.dto;


import nano.entity.Item;
import nano.entity.ItemImport;
import nano.entity.ItemImportDetail;

public class ItemImportDetailDTO {
	private Integer id;
	private Item item;
	private float unitPrice;
	private int quantity;
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
	
	public ItemImportDetail toEntity(ItemImport ii) {
		ItemImportDetail fid = new ItemImportDetail();
		fid.setItem(this.item);
		fid.setItemImport(ii);
		fid.setId(this.id);
		fid.setQuantity(this.quantity);
		fid.setUnitPrice(this.unitPrice);
		
		return fid;
	}
	
	
}
