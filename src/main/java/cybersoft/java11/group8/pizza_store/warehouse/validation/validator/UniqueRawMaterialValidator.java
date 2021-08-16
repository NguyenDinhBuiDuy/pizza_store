package cybersoft.java11.group8.pizza_store.warehouse.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.UniqueRawMaterialName;

public class UniqueRawMaterialValidator implements ConstraintValidator<UniqueRawMaterialName, String> {

	@Autowired
	private RawMaterialRepository rawMaterialRepository;
	
	private String message;
	
	@Override
	public void initialize(UniqueRawMaterialName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String rawMaterialName, ConstraintValidatorContext context) {
		Optional<RawMaterial> isExistName = rawMaterialRepository.findByName(rawMaterialName);
		if (isExistName.isEmpty())
			return true;
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		return false;
	}
}
