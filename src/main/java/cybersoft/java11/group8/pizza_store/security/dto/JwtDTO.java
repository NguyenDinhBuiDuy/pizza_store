package cybersoft.java11.group8.pizza_store.security.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtDTO {
	private String jwt;
	
	public JwtDTO jwt(String jwt) {
		this.jwt = jwt;
		return this;
	}
}
