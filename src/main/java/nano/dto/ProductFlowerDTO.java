package nano.dto;

public class ProductFlowerDTO {
	private ProductDTO product;
	
	private FlowerDTO flower;
	
	private int quantity;

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public FlowerDTO getFlower() {
		return flower;
	}

	public void setFlower(FlowerDTO flower) {
		this.flower = flower;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
