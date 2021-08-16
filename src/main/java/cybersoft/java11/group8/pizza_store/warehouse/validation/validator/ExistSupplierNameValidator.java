package cybersoft.java11.group8.pizza_store.warehouse.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.warehouse.model.Supplier;
import cybersoft.java11.group8.pizza_store.warehouse.repository.SupplierRepository;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.ExistSupplierName;

public class ExistSupplierNameValidator implements ConstraintValidator<ExistSupplierName, String> {
	
	@Autowired
	private SupplierRepository supplierRepository;

	private String message;
	
	@Override
	public void initialize(ExistSupplierName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String supplierName, ConstraintValidatorContext context) {
		Optional<Supplier> findSupplierByName = supplierRepository.findByName(supplierName);
		if (findSupplierByName.isPresent())
			return true;
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		return false;
	}
}
