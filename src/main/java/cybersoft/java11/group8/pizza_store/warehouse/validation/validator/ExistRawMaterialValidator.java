package cybersoft.java11.group8.pizza_store.warehouse.validation.validator;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.ExistRawMaterial;

public class ExistRawMaterialValidator implements ConstraintValidator<ExistRawMaterial, Set<String>> {
	@Autowired
	private RawMaterialRepository rawMaterialRepository;

	private String message;

	@Override
	public boolean isValid(Set<String> rawMaterialName, ConstraintValidatorContext context) {
		Iterator<String> iterator = rawMaterialName.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			if((rawMaterialRepository.findRawMaterialByName(iterator.next())) == null) {
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
