package cybersoft.java11.group8.pizza_store.role.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import cybersoft.java11.group8.pizza_store.role.validation.annotation.UniqueRolename;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateRoleDTO {
	@NotBlank(message = "{role.name.notblank}")
	@Length(min = 4, max = 50, message = "{role.name.size}")
	@UniqueRolename(message = "role name is already used")
	private String rolename;

	@NotBlank(message = "{role.description.notblank}")
	@Length(min = 4, max = 255, message = "{role.description.length}")
	private String description;
	/*
	 * @NotNull-> chỉ check null, không check rỗng
	 * 
	 * 
	 */

}
