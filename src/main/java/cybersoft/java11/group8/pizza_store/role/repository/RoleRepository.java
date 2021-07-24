package cybersoft.java11.group8.pizza_store.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;

import cybersoft.java11.group8.pizza_store.role.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRolename(String roleName);

	List<Role> findByDescriptionContainingOrderByIdAsc(String description);

	@Query("SELECT r FROM Role r WHERE r.rolename=:rolename AND r.description IS NOT NULL")
	List<Role> findRoleWithNotNullDescription(@Param("rolename") String rolename);

	int countByRolename(String rolename);

	@Override
	@EntityGraph(attributePaths = { "goups" }, type = EntityGraphType.FETCH)
	public List<Role> findAll();
}
