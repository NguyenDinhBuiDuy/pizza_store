package cybersoft.java11.group8.pizza_store.role.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import cybersoft.java11.group8.pizza_store.role.validation.annotation.UniqueRoleGroupName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleGroupDTO {
	
	@NotBlank(message = "{role_group.name.notblank}")
	@Length ( min = 4 , max = 50, message = "{role_group.name.length}")
	@UniqueRoleGroupName
	private String roleGroupName;
	
	@NotBlank(message = "{role_group.description.notblank}")
	@Length ( min = 4 , max = 255, message = "{role_group.description.length}")
	private String description;
	
	@NotNull
	private Set<String> roleNames = new HashSet<>();

}
