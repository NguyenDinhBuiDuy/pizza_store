package cybersoft.java11.group8.pizza_store.role.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "gira_account")
public class Account extends AbstractEntity  {

	@NotBlank
	@Column(unique = true)
	private String username;
	
	@NotBlank
	private String password;
	
	private String email;


	@ManyToOne ( fetch = FetchType.LAZY) // the n-1 relative, one account can have many role
	@JoinColumn(name = "role_id", insertable = false, updatable = false) // creating a fk with role_id,
	private Role role;
	
//	@Column(name = "role_id", insertable = false, updatable = false)
//	private Long roleId; // creating a fk with role_id, the value can't be insert or update here
}
