package cybersoft.java11.group8.pizza_store.util;

import java.util.LinkedList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ErrorUtils {
	public static List<String> getErrorMessages (BindingResult bidingResult){
		List<String> messages = new LinkedList<>();
		List<ObjectError> erros = bidingResult.getAllErrors();
		for (ObjectError error: erros) {
			messages.add(error.getDefaultMessage());
		}
		
		return messages;
	}
	
	public static List<String> errorOf (String message){
		List<String> messages = new LinkedList<>();
		messages.add(message);
		return messages;
	}

}
