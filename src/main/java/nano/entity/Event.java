package nano.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import nano.dto.EventDTO;

/* 
Created By Nguyen Viet Minh Quang
 */
@Entity
@Table(name = "event")
public class Event implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Integer id;
    
    @Column
    @NotBlank(message = "Please fill in Event Name")
    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<Product> products = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.DETACH)
    private List<Flower> flowers = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }

    

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public EventDTO toDTO() {
    	EventDTO dto = new EventDTO();
    	dto.setId(this.id);
    	dto.setName(this.name);
    	return dto;
    }
    
    @PreRemove
    private void preRemove() {
       flowers.forEach(f -> {
    	   f.setEvent(null);
       });
       products.forEach(p -> {
    	   p.setEvent(null);
       });
    }
    

}
