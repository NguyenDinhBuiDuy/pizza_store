package cybersoft.java11.group8.pizza_store.role.model;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_role")
public class Role extends AbstractEntity{
	
	@NotBlank(message = "role name can't be blank")
	@Length ( min = 4 , max = 50, message = "role name length is between {0} and {1}.")
	@Column (unique = true)
	private String rolename;
	
	@NotBlank(message = "role description can't be blank")
	@Length ( min = 4 , max = 255, message = "role description length is between {0} and {1}.")
	private String description;
	
	@ManyToMany( mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<RoleGroup> groups = new HashSet<>();
	
	public Role roleName(String roleName) {
		this.rolename = roleName;
		return this;
	}
	
	public Role description(String description) {
		this.description = description;
		return this;
	}
	
	public Role id(Long roleId) {
		this.id = roleId;
		return this;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("ROLE: %d %s %s", id, rolename, description);
	}

	

	

}
