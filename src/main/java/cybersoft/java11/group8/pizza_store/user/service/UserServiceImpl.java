package cybersoft.java11.group8.pizza_store.user.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.user.dto.CreateUserDTO;
import cybersoft.java11.group8.pizza_store.user.model.User;
import cybersoft.java11.group8.pizza_store.user.repository.UserRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {
	@Autowired
	UserRepository _repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	private MapDTOToModel mapper;

	@Override
	public User save(CreateUserDTO dto) {
		User user = new User();
		user = (User) mapper.map(dto, user);
		
		return _repository.save(user);
	}

	@Override
	public boolean isTakenUsername(String username) {
		
		return _repository.countByUsername(username) >= 1;
	}

	@Override
	public User update(@Valid CreateUserDTO dto, Long userId) {
		User user = _repository.getOne(userId);
		User updateUser = new User();
		updateUser = (User) mapper.map(dto, user);
		
		return _repository.save(updateUser);
	}

	@Override
	public boolean existUser(@Valid @NotNull Long userId) {
		return _repository.existsById(userId);
	}

}
