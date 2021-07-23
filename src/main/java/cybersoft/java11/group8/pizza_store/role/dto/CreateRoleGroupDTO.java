package cybersoft.java11.group8.pizza_store.role.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class CreateRoleGroupDTO {
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank(message = "role name can't be blank")
	@Length ( min = 4 , max = 50, message = "role name length is between {0} and {1}.")
	private String groupName;
	
	@NotBlank(message = "role description can't be blank")
	@Length ( min = 4 , max = 255, message = "role description length is between {0} and {1}.")
	private String description;

}
