package cybersoft.java11.group8.pizza_store.role.dto;

import java.util.Set;

import cybersoft.java11.group8.pizza_store.role.model.Account;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RoleWithAccountsDTO {
	private long id;
	private String roleName; 
	private String description;
	private Set<Account> accounts;

	


}
