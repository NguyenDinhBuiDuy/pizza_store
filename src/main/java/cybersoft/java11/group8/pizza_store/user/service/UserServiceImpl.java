package cybersoft.java11.group8.pizza_store.user.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.user.dto.CreateUserDTO;
import cybersoft.java11.group8.pizza_store.user.dto.UpdateUserDTO;
import cybersoft.java11.group8.pizza_store.user.model.User;
import cybersoft.java11.group8.pizza_store.user.repository.UserRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import lombok.AllArgsConstructor;

@Service

public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

	private UserRepository _repository;
	
	private PasswordEncoder passwordEncoder;

	private MapDTOToModel mapper;
	
	@Autowired
	public UserServiceImpl(JpaRepository<User, Long> repository, UserRepository _repository,
			PasswordEncoder passwordEncoder, MapDTOToModel mapper) {
		super(repository);
		this._repository = _repository;
		this.passwordEncoder = passwordEncoder;
		this.mapper = mapper;
	}
	
	

	@Override
	public User save(CreateUserDTO dto) {
		User user = new User();
		user = (User) mapper.map(dto, user);
		
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		return _repository.save(user);
	}

	@Override
	public boolean isTakenUsername(String username) {
		
		return _repository.countByUsername(username) >= 1;
	}


	@Override
	public boolean existUser(@Valid @NotNull Long userId) {
		return _repository.existsById(userId);
	}

	@Override
	public User update(@Valid @NotNull Long userId, @Valid UpdateUserDTO dto) {
		User user = _repository.getOne(userId);
		user = (User) mapper.map(dto, user);
		
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		return _repository.save(user);
	}

}
