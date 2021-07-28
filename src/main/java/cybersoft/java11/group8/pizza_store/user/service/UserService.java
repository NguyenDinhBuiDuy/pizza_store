package cybersoft.java11.group8.pizza_store.user.service;

import javax.validation.Valid;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.user.dto.CreateUserDTO;
import cybersoft.java11.group8.pizza_store.user.model.User;

public interface UserService extends GenericService<User, Long> {


	User save(CreateUserDTO dto);

	boolean isTakenUsername(String username);

	User update(@Valid CreateUserDTO dto, Long userId);

}
