package cybersoft.java11.group8.pizza_store.user.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java11.group8.pizza_store.common_data.model.ResponseHandler;
import cybersoft.java11.group8.pizza_store.user.dto.CreateUserDTO;
import cybersoft.java11.group8.pizza_store.user.dto.UpdateUserDTO;
import cybersoft.java11.group8.pizza_store.user.model.User;
import cybersoft.java11.group8.pizza_store.user.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService _service;
	
	@GetMapping("")
	public ResponseEntity<Object> findAllUser(){
		List<User> users = _service.findAll();
		if (users.isEmpty()) {
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(users, HttpStatus.OK);
	}
	@GetMapping("{user-id}")
	public ResponseEntity<Object> findUserById(@Valid @PathVariable("user-id") Long userId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Optional<User> user = _service.findById(userId);
		if (user.isEmpty()) {
			return ResponseHandler.getResponse( "there is no data", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHandler.getResponse(user, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody CreateUserDTO dto, BindingResult errors){
		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user = _service.save(dto);
		return ResponseHandler.getResponse(user, HttpStatus.CREATED);
	}
	
	@PutMapping("/{user-id}")
	public ResponseEntity<Object> updateUser(@Valid UpdateUserDTO dto, @Valid @NotNull @PathVariable ("user-id") Long userId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_service.existUser(userId))
			return ResponseHandler.getResponse("there is no user id: " + userId, HttpStatus.BAD_REQUEST);
		
		User user = new User();
		user = _service.update(userId, dto);
		return ResponseHandler.getResponse(user, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{user-id}")
	public ResponseEntity<Object> deleteUser(@Valid @NotNull @PathVariable ("user-id") Long userId, BindingResult errors){
		
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_service.existUser(userId))
			return ResponseHandler.getResponse("there is no user id: " + userId, HttpStatus.BAD_REQUEST);
		
		_service.deleteById(userId);
		return ResponseHandler.getResponse( HttpStatus.OK);
	}

}
