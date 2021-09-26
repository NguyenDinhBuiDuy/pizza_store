package cybersoft.java11.group8.pizza_store.user.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.role.model.Role;
import cybersoft.java11.group8.pizza_store.role.model.RoleGroup;
import cybersoft.java11.group8.pizza_store.user.util.UserStatus;
import cybersoft.java11.group8.pizza_store.user.validation.annotation.PasswordMatch;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name = "pizza_store_user")
public class User extends AbstractEntity {
	@NotBlank(message = "{user.username.notblank}")
	@Size(min = 3, max = 50, message = "{user.username.size}")
	@Column(unique = true)
	private String username;

	@NotBlank(message = "{user.password.notblank}")
	private String password;
	
	@NotBlank(message = "{user.password.notblank}")
	private String confirmPassword;
	
	@NotBlank(message = "{user.email.notblank}")
	@Email
	private String email;
	
	@NotBlank(message = "{user.fullname.notblank}")
	private String fullname;
	
	@NotBlank(message = "{user.displayname.notblank}")
	private String displayname;
	
	private String avatar;

	@NotNull(message = "{user.status.notnull}")
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	private String facebookUrl;
	private String job;
	private String department;
	private String hobbies;
	
	@ManyToMany(mappedBy = "users")
	@JsonIgnore
	private Set<RoleGroup> roleGroups = new HashSet<>();
	
	@ManyToOne ( fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;
	

	
	
}
