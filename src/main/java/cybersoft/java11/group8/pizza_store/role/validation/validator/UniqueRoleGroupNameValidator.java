package cybersoft.java11.group8.pizza_store.role.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.role.service.RoleGroupService;
import cybersoft.java11.group8.pizza_store.role.validation.annotation.UniqueRoleGroupName;

public class UniqueRoleGroupNameValidator implements ConstraintValidator<UniqueRoleGroupName, String> {

	private RoleGroupService _roleGroupService;
	private String message;
	
	@Autowired
	public UniqueRoleGroupNameValidator(RoleGroupService _roleGroupService) {
		super();
		this._roleGroupService = _roleGroupService;
	}

	@Override
	public boolean isValid(String roleGroupName, ConstraintValidatorContext context) {
		boolean isTakenRolename = _roleGroupService.isTakenRoleGroupName(roleGroupName);
		if (!isTakenRolename)
			return true;

		context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
				.disableDefaultConstraintViolation();
		
		return false;
	}

	@Override
	public void initialize(UniqueRoleGroupName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

}
