package cybersoft.java11.group8.pizza_store.role.service;

import javax.validation.Valid;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.role.model.Role;
import cybersoft.java11.group8.pizza_store.role.model.RoleGroup;

public interface RoleGroupService extends GenericService<RoleGroup, Long> {

	RoleGroup addRole(@Valid Role role, Long groupId);

	RoleGroup addUsername(@Valid String username, Long groupId);

}
