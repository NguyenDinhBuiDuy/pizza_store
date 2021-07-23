package cybersoft.java11.group8.pizza_store.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import cybersoft.java11.group8.pizza_store.user.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	int countByUsername(String username);
	
	@EntityGraph (attributePaths = {"user.roleGroups"},
			type =EntityGraphType.FETCH )
	Optional<User> findByUsername(String username);
	
	

}

