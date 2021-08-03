package cybersoft.java11.group8.pizza_store.role.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.role.dto.CreateRoleDTO;
import cybersoft.java11.group8.pizza_store.role.model.Role;
import cybersoft.java11.group8.pizza_store.role.repository.RoleRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository _roleRepository;
	
	private MapDTOToModel mapper;

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
		return _roleRepository.findByRolename(roleName).get();
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
	public boolean deleteRoleById(Long roleId) {
		Optional<Role> role = _roleRepository.findById(roleId);
		if (role != null) {
			_roleRepository.deleteById(roleId);
			return true;
		}
		return false;
	}

	@Override
	public Role save(@Valid CreateRoleDTO dto) {
		Role model = new Role();
		model = (Role) mapper.map(dto, model);
		return _roleRepository.save(model);
	}

	@Override
	public boolean isTakenRolename(String rolename) {
		return _roleRepository.countByRolename(rolename) >= 1;
	}

	@Override
	public boolean existById(Long roleId) {
		return _roleRepository.existsById(roleId);
	}

}
