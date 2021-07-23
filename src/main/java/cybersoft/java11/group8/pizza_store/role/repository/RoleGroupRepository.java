package cybersoft.java11.group8.pizza_store.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import cybersoft.java11.group8.pizza_store.role.model.RoleGroup;

@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long> {
	
	@Override
	@EntityGraph (attributePaths = {"roles", "users"},
	type = EntityGraphType.FETCH)
	public List<RoleGroup> findAll() ;
	

}
