package cybersoft.java11.group8.pizza_store.role.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.role.dto.CreateRoleGroupDTO;
import cybersoft.java11.group8.pizza_store.role.model.RoleGroup;

public interface RoleGroupService extends GenericService<RoleGroup, Long> {

	RoleGroup addRole(@Valid String rolename, Long groupId);

	RoleGroup addUsername(@Valid String username, Long groupId);

	boolean isTakenRoleGroupName(String roleGroupName);

	RoleGroup save(CreateRoleGroupDTO dto);

	boolean findRoleInRoleGroup(@Valid @NotNull Long id, @NotNull String roleName);

	boolean findUserInRoleGroup(@Valid @NotNull Long id, @NotNull String userName);

	boolean deleteRoleGroupById(@Valid Long groupId);

	boolean deleteRoleInRoleGroup(Long groupId, @Valid String roleName);

	boolean deleteUserInRoleGroup(Long groupId, @Valid String userName);

	boolean existRoleGroup(Long groupId);

	RoleGroup updateRoleGroup(@Valid CreateRoleGroupDTO dto, Long groupId);

}
