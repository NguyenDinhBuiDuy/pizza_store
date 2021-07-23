package cybersoft.java11.group8.pizza_store.role.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.role.dto.CreateRoleDTO;
import cybersoft.java11.group8.pizza_store.role.dto.RoleWithAccountsDTO;
import cybersoft.java11.group8.pizza_store.role.model.Role;
import cybersoft.java11.group8.pizza_store.role.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository _repository;
	@Override
	public void save(Role role) {
		_repository.save(role);
		
		
	}
	@Override
	public List<Role> findAll() {
		return _repository.findAll();
	}

	@Override
	public List<Role> findByRoleName(String roleName) {
		// TODO: bài tập
		return _repository.findByRolename(roleName);
	}

	@Override
	public List<Role> findByDescription(String description) {
		return _repository.findByDescriptionContainingOrderByIdAsc(description);
	}

	@Override
	public List<Role> findRoleWithoutBlankDescription(String roleName) {
		return _repository.findRoleWithNotNullDescription(roleName);
	}

	@Override
	public List<RoleWithAccountsDTO> findRoleWithAccountInfo() {
		List<Role> roles = _repository.findAll();
		List<RoleWithAccountsDTO> results = mapRoleToRoleWithAccountsDTO(roles);
		System.out.println("Hold this line to debug.");
		return results;
	}
	
	private List<RoleWithAccountsDTO> mapRoleToRoleWithAccountsDTO(List<Role> roles){
		List<RoleWithAccountsDTO> results = new LinkedList<RoleWithAccountsDTO>();
		
		for (Role role : roles) {
			RoleWithAccountsDTO dto = new RoleWithAccountsDTO();
			mapRoleToDto(dto, role);
			results.add(dto);
		}
		
		return results;
	}
	
	private void mapRoleToDto(RoleWithAccountsDTO dto, Role role) {
		dto.setId(role.getId());
		dto.setRoleName(role.getRolename());
		dto.setDescription(role.getDescription());
		dto.setAccounts(role.getAccounts());
	}
	@Override
	public Role updateRoleInfo(CreateRoleDTO dto, Long roleId) {
		Role role = _repository.getOne(roleId); // getOne: khi lay du lieu no se lay o context ma khong can sinh sql nhu findById de lay du lieu tu database
		role.id(roleId)
				.roleName(dto.getRolename())
				.description(dto.getDescription());
		
		return _repository.save(role);
	}
	@Override
	public boolean deleteRoleById( Long roleId) {
		Optional<Role> role = _repository.findById(roleId);
		if (role != null) {
		_repository.deleteById(roleId);
		return true ;
		}
		return false;
	}
	@Override
	public Role save(@Valid CreateRoleDTO dto) {
		Role role = new Role();
		role.roleName(dto.getRolename()).description(dto.getDescription());
		return _repository.save(role);
	}
	@Override
	public boolean isTakenRolename(String rolename) {
		return _repository.countByRolename(rolename) >= 1;
	}

	

}
