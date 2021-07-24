package cybersoft.java11.group8.pizza_store.role.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;


import cybersoft.java11.group8.pizza_store.role.validation.annotation.UniqueRoleName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleDTO {
	
	@NotBlank
	private Long id;
	
	@NotBlank(message = "{role.name.notblank}")
	@Length(min = 4, max = 50, message = "{role.name.size}")
	@UniqueRoleName(message = "role name is already used")
	private String rolename;

	@NotBlank(message = "{role.description.notblank}")
	@Length(min = 4, max = 255, message = "{role.description.length}")
	private String description;
}
