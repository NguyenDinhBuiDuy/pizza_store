package cybersoft.java11.group8.pizza_store.role.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.role.dto.CreateRoleGroupDTO;
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
	public RoleGroup addRole(@Valid Role role, Long groupId) {
		RoleGroup roleGroup = _roleGroupRepository.getOne(groupId);
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
		
		Role role = new Role();
		Iterator<String> iterator = dto.getRoleNames().iterator();
		while(iterator.hasNext()) {
			iterator.next();
           role = _roleRepository.findByRolename(iterator.next());
           model.addRole(role);
        }
		return _roleGroupRepository.save(model);	
	}

}
