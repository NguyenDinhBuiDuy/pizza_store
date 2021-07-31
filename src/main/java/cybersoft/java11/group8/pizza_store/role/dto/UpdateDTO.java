package cybersoft.java11.group8.pizza_store.role.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import cybersoft.java11.group8.pizza_store.role.validation.annotation.CExistRoleGroupId;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UpdateDTO {
	@CExistRoleGroupId
	private Long Id;
	
	@NotBlank(message = "{role_group.name.notblank}")
	@Length ( min = 4 , max = 50, message = "{role_group.name.length}")
	private String roleGroupName;
	
	@NotBlank(message = "{role_group.description.notblank}")
	@Length ( min = 4 , max = 255, message = "{role_group.description.length}")
	private String description;

}
