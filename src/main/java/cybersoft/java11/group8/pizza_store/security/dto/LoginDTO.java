package cybersoft.java11.group8.pizza_store.security.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
}
