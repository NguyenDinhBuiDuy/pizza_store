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
import cybersoft.java11.group8.pizza_store.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "gira_role_group")
public class RoleGroup extends AbstractEntity {
	
	@NotBlank(message = "role group name can't be blank")
	@Length ( min = 4 , max = 50, message = "role name length is between {0} and {1}.")
	private String groupName;
	
	@NotBlank(message = "role group description can't be blank")
	@Length ( min = 4 , max = 255, message = "role description length is between {0} and {1}.")
	private String description;
	
	@ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST}) // không sử dụng cascade Remove và all và tạo Jion table ở đối tượng chính
	@JoinTable (name = "group_links",
	joinColumns = @JoinColumn (name = "group_id"),
	inverseJoinColumns = @JoinColumn (name = "role_id"))
	private Set<Role> roles = new HashSet<>(); // trong một mối quan hệ many to many thì luôn dùng set list sẽ ảnh hưởng đến performance
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "gira_user_role_group",
				joinColumns = @JoinColumn(name = "role_group_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<>();
	/*
	 * helper methods: để đảm bảo dữ liệu luôn được thống nhất giữa các đối tượng 
	 * vd muốn thêm một role mới,
	 */
	public RoleGroup addRole (Role role) {
		this.roles.add(role);
		role.getGroups().add(this);
		return this;
	}
	public RoleGroup name (String name) {
		this.groupName = name;
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
//	public Role description (String decription) {
//		this.description = description;
//		return this;
//	}
}
