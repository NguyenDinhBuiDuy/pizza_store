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
	
	@NotBlank(message = "{role.name.notblank}")
	@Length ( min = 4 , max = 50, message = "{role.name.length}")
	@Column (unique = true)
	private String rolename;
	
	@NotBlank(message = "{role.description.notblank}")
	@Length ( min = 4 , max = 255, message = "{role.description.length}")
	private String description;
	
	@ManyToMany( mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<RoleGroup> groups = new HashSet<>();

}
