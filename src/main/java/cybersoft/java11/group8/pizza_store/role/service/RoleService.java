package cybersoft.java11.group8.pizza_store.role.service;

import java.util.List;

import javax.validation.Valid;

import cybersoft.java11.group8.pizza_store.role.dto.CreateRoleDTO;
import cybersoft.java11.group8.pizza_store.role.model.Role;

public interface RoleService {
	void save(Role role);
	List<Role> findAll();
	Role findByRoleName(String roleName);
	List<Role> findByDescription(String description);
	List<Role> findRoleWithoutBlankDescription(String roleName);
	boolean deleteRoleById(Long roleId);
	Role save(@Valid CreateRoleDTO dto);
	boolean isTakenRolename(String rolename);
	boolean existById(Long roleId);
}
