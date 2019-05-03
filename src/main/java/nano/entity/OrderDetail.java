package nano.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="order_details")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_details_id")
	private int orderdetailsId;
	
	@ManyToOne
    @JoinColumn(name ="order_id")
    private Order order;
	
	@ManyToOne
    @JoinColumn(name ="product_id")
    private Product product;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="unit")
	private float unit;
	
	@Transient
    private double total;
			
	public int getOrderdetailsId() {
		return orderdetailsId;
	}
	public void setOrderdetailsId(int orderdetailsId) {
		this.orderdetailsId = orderdetailsId;
	}		
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getUnit() {
		return unit;
	}
	public void setUnit(float unit) {
		this.unit = unit;
	}	
	
	public double getTotal() {
		return unit * quantity;
	}
	
}
