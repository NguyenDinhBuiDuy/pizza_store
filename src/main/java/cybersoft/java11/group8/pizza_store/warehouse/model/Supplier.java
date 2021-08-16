package cybersoft.java11.group8.pizza_store.warehouse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_supplier")
public class Supplier extends AbstractEntity {
	@NotBlank(message = "{supplier.name.not-blank}")
	@Size(min = 3, message = "{supplier.name.size}")
	@Column(unique = true)
	private String name;
	
	@NotBlank(message = "{supplier.address.not-blank}")
	private String address;
	
	@Email
	private String email;
	
	@NotBlank(message = "{supplier.phone.not-blank}")
	@Size(min = 10, max = 10, message = "{supplier.phone.size}")
	@Column(unique = true)
	private String phone;
	
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	private List<RawMaterial> rawMaterial = new ArrayList<>();
}
