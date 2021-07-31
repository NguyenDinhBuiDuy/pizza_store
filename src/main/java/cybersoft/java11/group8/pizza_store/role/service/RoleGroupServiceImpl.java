package cybersoft.java11.group8.pizza_store.role.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;


import cybersoft.java11.group8.pizza_store.role.dto.CreateRoleGroupDTO;
import cybersoft.java11.group8.pizza_store.role.dto.UpdateDTO;
import cybersoft.java11.group8.pizza_store.role.model.Role;
import cybersoft.java11.group8.pizza_store.role.model.RoleGroup;
import cybersoft.java11.group8.pizza_store.role.repository.RoleGroupRepository;
import cybersoft.java11.group8.pizza_store.role.repository.RoleRepository;
import cybersoft.java11.group8.pizza_store.user.model.User;
import cybersoft.java11.group8.pizza_store.user.repository.UserRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoleGroupServiceImpl implements RoleGroupService {

	private RoleGroupRepository _roleGroupRepository;

	private UserRepository _userRepository;

	private RoleRepository _roleRepository;

	private MapDTOToModel mapper;
	
	@Override
	public List<RoleGroup> findAll() {
		return _roleGroupRepository.findAll();
	}

	@Override
	public Optional<RoleGroup> findById(Long id) {
		// TODO Auto-generated method stub
		return _roleGroupRepository.findById(id);
	}

	@Override
	public RoleGroup update(RoleGroup entity) {
		// TODO Auto-generated method stub
		return _roleGroupRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		_roleGroupRepository.deleteById(id);

	}
	@Override
	public RoleGroup addRole(@Valid @NotNull String rolename, Long groupId) {
		RoleGroup roleGroup = _roleGroupRepository.getOne(groupId);
		Role role = _roleRepository.findByRolename(rolename).get();
		roleGroup.addRole(role);
		
		return _roleGroupRepository.save(roleGroup);

	}

	@Override
	public RoleGroup addUsername(@Valid String username, Long groupId) {
		RoleGroup roleGroup = _roleGroupRepository.getOne(groupId);
		Optional<User> user = _userRepository.findByUsername(username);
		if (user.isPresent())
			roleGroup.addUser(user);
		return _roleGroupRepository.save(roleGroup);
	}

	@Override
	public boolean isTakenRoleGroupName(String roleGroupName) {
		return _roleGroupRepository.countByRoleGroupName(roleGroupName) >= 1;
	}

	@Override
	public RoleGroup save(RoleGroup entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleGroup save(CreateRoleGroupDTO dto) {
		RoleGroup model = new RoleGroup();
		model = (RoleGroup) mapper.map(dto, model);
		return _roleGroupRepository.save(model);
	}

	@Override
	public boolean findRoleInRoleGroup(@Valid @NotNull Long id,
			@NotNull String roleName) {
		RoleGroup roleGroup = _roleGroupRepository.getOne(id);
		
		Set<Role> roles = roleGroup.getRoles();
		for (Role role: roles) {
			if (role.getRolename().equals(roleName))
				
				return true;
		}
		return false;
	}
	
	@Override
	public boolean findUserInRoleGroup(@Valid @NotNull Long id,
			@NotNull String userName) {
		RoleGroup roleGroup = _roleGroupRepository.getOne(id);
		
		for (User user: roleGroup.getUsers()) {
			if (user.getUsername().equals(userName))
				
				return true;
		}
		return false;
	}

	@Override
	public boolean deleteRoleGroupById(@Valid Long groupId) {
		Optional <RoleGroup> roleGroup = Optional.of(_roleGroupRepository.getOne(groupId));
		
		if (roleGroup.isPresent())
			return true;
		return false;
	}

	@Override
	public boolean deleteRoleInRoleGroup(Long groupId, @Valid String roleName) {
		RoleGroup roleGroup = _roleGroupRepository.getOne(groupId);
		
		Set<Role> roles = roleGroup.getRoles();
		for (Role role: roles) {
			if (role.getRolename().equals(roleName))
				roleGroup.getRoles().remove(role);
				return true;
		}
		return false;
	}

	@Override
	public boolean deleteUserInRoleGroup(Long groupId, @Valid String userName) {
		RoleGroup roleGroup = _roleGroupRepository.getOne(groupId);
		
		Set<User> users = roleGroup.getUsers();
		for (User user : users) {
			if (user.getUsername().equals(userName))
				roleGroup.getUsers().remove(user);
				return true;
		}
		return false;
	}

	@Override
	public RoleGroup updateRoleGroup(@Valid CreateRoleGroupDTO dto, Long groupId) {
		RoleGroup group = _roleGroupRepository.getOne(groupId);
		group = (RoleGroup) mapper.map(dto, group);
		return _roleGroupRepository.save(group);
	}

	@Override
	public boolean existRoleGroup(Long groupId) {
		
		return _roleGroupRepository.existsById(groupId);
	}


}
