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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.role.model.Role;
import cybersoft.java11.group8.pizza_store.role.model.RoleGroup;
import cybersoft.java11.group8.pizza_store.user.util.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_user")
public class User extends AbstractEntity {
	@NotBlank(message = "{user.username.notblank}")
	@Size(min = 3, max = 50, message = "{user.username.size}")
	@Column(unique = true)
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String fullname;

	@NotBlank
	private String displayname;

	private String avatar;

	@NotNull
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
	

	public User username(String username) {
		this.username = username;
		return this;
	}
	public User password(String password) {
		this.password = password;
		return this;
	}
	public User email(String email) {
		this.email = email;
		return this;
	}
	public User fullname (String fullname) {
		this.fullname = fullname;
		return this;
	}
	public User displayname (String displayname) {
		this.displayname = displayname;
		return this;
	}
	public User status (UserStatus status) {
		this.status = status;
		return this;
	}
	
	
}
