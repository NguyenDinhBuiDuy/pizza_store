package cybersoft.java11.group8.pizza_store.user.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanUtils;

import cybersoft.java11.group8.pizza_store.user.validation.annotation.PasswordMatch;



public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Class<?> >{

	    private String firstFieldName;
	    private String secondFieldName;
	    private String message;

	    @Override
	    public void initialize( PasswordMatch constraintAnnotation) {
	        firstFieldName = constraintAnnotation.first();
	        secondFieldName = constraintAnnotation.second();
	        message = constraintAnnotation.message();
	    }

	    @Override
	    public boolean isValid( Class<?> value,  ConstraintValidatorContext context) {
	        boolean valid = true;
	        try
	        {
	            Object firstObj = BeanUtils.getPropertyDescriptor(value, firstFieldName);
	            Object secondObj = BeanUtils.getPropertyDescriptor(value, secondFieldName);

	            valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
	        }
	        catch (Exception ignore)
	        {
	            // ignore
	        }

	        if (!valid){
	            context.buildConstraintViolationWithTemplate(message)
	                    .addPropertyNode(firstFieldName)
	                    .addConstraintViolation()
	                    .disableDefaultConstraintViolation();
	        }

	        return valid;
	    }
}
