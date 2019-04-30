package nano.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductFlowerId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private Integer productId;
	
	@Column
	private Integer flowerId;

	

	public ProductFlowerId() {
		super();
	}

	public ProductFlowerId(Integer productId, Integer flowerId) {
		super();
		this.productId = productId;
		this.flowerId = flowerId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getFlowerId() {
		return flowerId;
	}

	public void setFlowerId(Integer flowerId) {
		this.flowerId = flowerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flowerId == null) ? 0 : flowerId.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductFlowerId other = (ProductFlowerId) obj;
		if (flowerId == null) {
			if (other.flowerId != null)
				return false;
		} else if (!flowerId.equals(other.flowerId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	
	
	
}
