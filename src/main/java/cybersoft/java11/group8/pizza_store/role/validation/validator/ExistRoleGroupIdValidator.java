package cybersoft.java11.group8.pizza_store.role.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cybersoft.java11.group8.pizza_store.role.service.RoleGroupService;
import cybersoft.java11.group8.pizza_store.role.validation.annotation.CExistRoleGroupId;

public class ExistRoleGroupIdValidator implements 
ConstraintValidator<CExistRoleGroupId, Long>{
	
	private RoleGroupService _roleGroupService;
	private String message;
	
	@Autowired
	public ExistRoleGroupIdValidator(RoleGroupService _roleGroupService) {
		super();
		this._roleGroupService = _roleGroupService;
	}


	@Override
	public void initialize(CExistRoleGroupId constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	
	@Override
	public boolean isValid(Long roleGroupId, ConstraintValidatorContext context) {
		
		Boolean isExistRoleGroup = _roleGroupService.existRoleGroup(roleGroupId);
		if (isExistRoleGroup)
			
			return true;
		
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		
		return false;
	}
	
	

}
