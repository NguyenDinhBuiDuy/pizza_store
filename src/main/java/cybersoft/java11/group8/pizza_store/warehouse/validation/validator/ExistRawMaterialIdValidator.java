package cybersoft.java11.group8.pizza_store.warehouse.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.ExistRawMaterialId;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.ExistRawMaterialName;

public class ExistRawMaterialIdValidator implements ConstraintValidator<ExistRawMaterialId, Long> {

	@Autowired
	private RawMaterialRepository _rawMaterialRepository;
	
	private String message;
	
	@Override
	public void initialize(ExistRawMaterialId constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(Long rawMaterialId, ConstraintValidatorContext context) {
		boolean IsExistRawMaterial = _rawMaterialRepository.existsById(rawMaterialId);
		
		if (IsExistRawMaterial)
			return true;
		
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		
		return false;
	}

}
