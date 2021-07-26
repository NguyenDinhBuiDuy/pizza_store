package cybersoft.java11.group8.pizza_store.warehouse.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;
	private String email;
	private String phone;
	
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	private List<RawMaterial> rawMaterial = new ArrayList<>();

	public Supplier id(Integer id) {
		this.id = id;
		return this;
	}
	
	public Supplier name(String name) {
		this.name = name;
		return this;
	}
	
	public Supplier address(String address) {
		this.address = address;
		return this;
	}
	
	public Supplier email(String email) {
		this.email = email;
		return this;
	}
	
	public Supplier phone(String phone) {
		this.phone = phone;
		return this;
	}
}
