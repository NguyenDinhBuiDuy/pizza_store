package cybersoft.java11.group8.pizza_store.role.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.role.dto.CreateRoleDTO;
import cybersoft.java11.group8.pizza_store.role.model.Role;
import cybersoft.java11.group8.pizza_store.role.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository _roleRepository;
	@Override
	public void save(Role role) {
		_roleRepository.save(role);
		
		
	}
	@Override
	public List<Role> findAll() {
		return _roleRepository.findAll();
	}

	@Override
	public Role findByRoleName(String roleName) {
		return _roleRepository.findByRolename(roleName);
	}

	@Override
	public List<Role> findByDescription(String description) {
		return _roleRepository.findByDescriptionContainingOrderByIdAsc(description);
	}

	@Override
	public List<Role> findRoleWithoutBlankDescription(String roleName) {
		return _roleRepository.findRoleWithNotNullDescription(roleName);
	}
	
	@Override
	public Role updateRoleInfo(CreateRoleDTO dto, Long roleId) {
		Role role = _roleRepository.getOne(roleId);
		role.id(roleId)
				.roleName(dto.getRolename())
				.description(dto.getDescription());
		
		return _roleRepository.save(role);
	}
	@Override
	public boolean deleteRoleById( Long roleId) {
		Optional<Role> role = _roleRepository.findById(roleId);
		if (role != null) {
		_roleRepository.deleteById(roleId);
		return true ;
		}
		return false;
	}
	@Override
	public Role save(@Valid CreateRoleDTO dto) {
		Role role = new Role();
		role.roleName(dto.getRolename()).description(dto.getDescription());
		return _roleRepository.save(role);
	}
	@Override
	public boolean isTakenRolename(String rolename) {
		return _roleRepository.countByRolename(rolename) >= 1;
	}

	

}
