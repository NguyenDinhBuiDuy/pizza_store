package cybersoft.java11.group8.pizza_store.warehouse.validation.validator;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.warehouse.repository.SupplierRepository;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.ExistSupplier;

public class ExistSupplierValidator implements ConstraintValidator<ExistSupplier, Set<String>> {
	@Autowired
	private SupplierRepository supplierRepository;

	private String message;

	@Override
	public boolean isValid(Set<String> supplierName, ConstraintValidatorContext context) {
		Iterator<String> iterator = supplierName.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			if((supplierRepository.findSuplierByName(iterator.next())) == null) {
				message = message + ("/n +" + iterator.next() + "is not exist");
				i++;
			}
		}
		if(i > 0) {
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
					.disableDefaultConstraintViolation();
			return false;
		}
		return true;
	}
}
