package cybersoft.java11.group8.pizza_store.warehouse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_supplier")
public class Supplier extends AbstractEntity {
	
	private String name;
	private String address;
	private String email;
	private String phone;
	
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	private List<RawMaterial> rawMaterial = new ArrayList<>();
}
