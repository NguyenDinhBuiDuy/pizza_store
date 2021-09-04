package cybersoft.java11.group8.pizza_store.warehouse.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSupplierDto {
	@Size(min = 3, message = "{supplier.name.size}")
	@Column(unique = true)
	private String name;
	
	private String address;
	
	@Email
	private String email;
	
	@Size(min = 10, max = 10, message = "{supplier.phone.size}")
	@Column(unique = true)
	private String phone;
}
