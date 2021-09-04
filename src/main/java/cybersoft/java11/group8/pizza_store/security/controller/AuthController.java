package cybersoft.java11.group8.pizza_store.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java11.group8.pizza_store.common_data.model.ResponseHandler;
import cybersoft.java11.group8.pizza_store.security.dto.JwtDTO;
import cybersoft.java11.group8.pizza_store.security.dto.LoginDTO;
import cybersoft.java11.group8.pizza_store.security.jwt.JwtUtils;

@RestController
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO dto,
										BindingResult errors){
		Authentication authentication = null;
		
		try {
			// authenticate
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
			
			// set authentication into SecurityContext
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwtToken = jwtUtils.generateJwtToken(authentication);
			return ResponseHandler.getResponse(new JwtDTO().jwt(jwtToken), HttpStatus.OK);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		return ResponseHandler.getResponse("Username or password is invalid.", HttpStatus.BAD_REQUEST);
	}
	
}
