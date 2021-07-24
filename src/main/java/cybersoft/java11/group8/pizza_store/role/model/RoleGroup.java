package cybersoft.java11.group8.pizza_store.role.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.role.validation.annotation.UniqueRoleGroupName;
import cybersoft.java11.group8.pizza_store.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "pizza_store_role_group")
public class RoleGroup extends AbstractEntity {
	
	@NotBlank(message = "{role_group.name.notblank}")
	@Length ( min = 4 , max = 50, message = "{role_group.name.length}")
	@UniqueRoleGroupName
	private String roleGroupName;
	
	@NotBlank(message = "{role_group.description.notblank}")
	@Length ( min = 4 , max = 255, message = "{role_group.description.length}")
	private String description;
	
	@ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST}) 
	@JoinTable (name = "pizza_store_rolegroup_role_links",
	joinColumns = @JoinColumn (name = "group_id"),
	inverseJoinColumns = @JoinColumn (name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "pizza_store_user_rolegroup_links",
				joinColumns = @JoinColumn(name = "role_group_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<>();

	public RoleGroup addRole (Role role) {
		this.roles.add(role);
		role.getGroups().add(this);
		return this;
	}
	public RoleGroup name (String name) {
		this.roleGroupName = name;
		return this;
	}
	
	public RoleGroup description(String description) {
		this.description = description;
		return this;
	}
	
	public RoleGroup addUser(Optional<User> user) {
		this.users.add(user.get());
		user.get().getRoleGroups().add(this);
		return this;
		
	}
}
