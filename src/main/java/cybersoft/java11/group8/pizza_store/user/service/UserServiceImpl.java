package cybersoft.java11.group8.pizza_store.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.user.dto.CreateUserDTO;
import cybersoft.java11.group8.pizza_store.user.model.User;
import cybersoft.java11.group8.pizza_store.user.repository.UserRepository;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {
	@Autowired
	UserRepository _repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User save(CreateUserDTO dto) {
		User user = new User();
		user.username(dto.getUsername())
		.password(passwordEncoder.encode(dto.getPassword()))
		.email(dto.getEmail())
		.displayname(dto.getDisplayname())
		.fullname(dto.getFullname())
		.status(dto.getStatus());
		
		return _repository.save(user);
	}

	@Override
	public boolean isTakenUsername(String username) {
		return _repository.countByUsername(username) >= 1;
	}

}
