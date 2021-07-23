package cybersoft.java11.group8.pizza_store.user.controller;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java11.group8.pizza_store.common_data.model.ResponseHandler;
import cybersoft.java11.group8.pizza_store.user.dto.CreateUserDTO;
import cybersoft.java11.group8.pizza_store.user.model.User;
import cybersoft.java11.group8.pizza_store.user.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService _service;
	
	@GetMapping("")
	public ResponseEntity<Object> findAll(){
		List<User> users = _service.findAll();
		if (users.isEmpty()) {
			return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(users, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> save(CreateUserDTO dto, BindingResult errors){
		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user = _service.save(dto);
		return ResponseHandler.getResponse(user, HttpStatus.CREATED);
	}

}
