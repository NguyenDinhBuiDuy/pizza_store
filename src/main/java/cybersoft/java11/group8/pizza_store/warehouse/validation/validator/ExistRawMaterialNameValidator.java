package cybersoft.java11.group8.pizza_store.warehouse.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.ExistRawMaterialName;

public class ExistRawMaterialNameValidator implements ConstraintValidator<ExistRawMaterialName, String> {

	@Autowired
	private RawMaterialRepository _rawMaterialRepository;
	
	private String message;
	
	@Override
	public void initialize(ExistRawMaterialName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String rawMaterialName, ConstraintValidatorContext context) {
		
		Optional<RawMaterial> rawMaterial = _rawMaterialRepository.findByName(rawMaterialName);
		if (rawMaterial.isPresent())
			return true;
		
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		
		return false;
	}

}
