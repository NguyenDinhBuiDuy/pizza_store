package cybersoft.java11.group8.pizza_store.warehouse.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.warehouse.model.Supplier;
import cybersoft.java11.group8.pizza_store.warehouse.repository.SupplierRepository;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.UniqueSupplierName;

public class UniqueSupplierValidator implements ConstraintValidator<UniqueSupplierName, String> {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	private String message;
	
	@Override
	public void initialize(UniqueSupplierName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String supplierName, ConstraintValidatorContext context) {
		Optional<Supplier> isExistName = supplierRepository.findByName(supplierName);
		if (isExistName.isEmpty())
			return true;
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		return false;
	}
}
