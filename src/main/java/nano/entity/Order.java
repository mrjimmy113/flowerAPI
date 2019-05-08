package nano.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="orders")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer orderId;
	
	@ManyToOne
    @JoinColumn(name ="account_id")
    private Account account;
	
	@Column(name="orderNo")
    private long orderNo;
	
	@Column(name="order_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
    private Date orderDate;
	
	@Column(name="shipped_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
    private Date shippedDate;	
	
	@Column(name="ship_address")
    private String shipAddress;	
    
    @Column(name="payment_type")
    private String paymentType;
    
    @Column(name="order_status")
    private String orderStatus;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> detail = new ArrayList<>();       
    
    public Order() {
	}   
    
    public List<OrderDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<OrderDetail> detail) {
		this.detail = detail;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}	
	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	
	

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", account=" + account + ", orderNo=" + orderNo + ", orderDate="
				+ orderDate + ", shippedDate=" + shippedDate + ", shipAddress=" + shipAddress + ", paymentType="
				+ paymentType + ", orderStatus=" + orderStatus + ", detail=" + detail + "]";
	}	

	
	
    
}

