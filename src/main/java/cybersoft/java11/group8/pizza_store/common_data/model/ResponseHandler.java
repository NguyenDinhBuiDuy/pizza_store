package cybersoft.java11.group8.pizza_store.common_data.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import cybersoft.java11.group8.pizza_store.util.ErrorUtils;
import cybersoft.java11.group8.pizza_store.util.ListUtils;

public class ResponseHandler {
	public static ResponseEntity<Object> getResponse(Object content, HttpStatus status){
		Map<String,Object> map = new HashMap<>();
		map.put("content", content);
		map.put("message", ListUtils.emtyString);
		map.put("status", status.value());
		
		return new ResponseEntity<>(map,status);
	}
	
	public static ResponseEntity<Object> getResponse(BindingResult bidingResult, HttpStatus status){
		Map<String,Object> map = new HashMap<>();
		map.put("content", ListUtils.emtyString);
		map.put("message", ErrorUtils.getErrorMessages(bidingResult));
		map.put("status", status.value());
		
		return new ResponseEntity<>(map,status);
	}
	
	public static ResponseEntity<Object> getResponse(String error, HttpStatus status){
		Map<String,Object> map = new HashMap<>();
		map.put("content", ListUtils.emtyString);
		map.put("message", ErrorUtils.errorOf(error));
		map.put("status", status.value());
		
		return new ResponseEntity<>(map,status);
	}
	
	public static ResponseEntity<Object> getResponse(HttpStatus status){
		Map<String,Object> map = new HashMap<>();
		map.put("content", ListUtils.emtyString);
		map.put("message", ListUtils.emtyString);
		map.put("status", status.value());
		
		return new ResponseEntity<>(map,status);
	}

}
