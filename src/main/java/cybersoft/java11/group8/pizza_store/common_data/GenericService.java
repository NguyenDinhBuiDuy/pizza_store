package cybersoft.java11.group8.pizza_store.common_data;

import java.util.List;
import java.util.Optional;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;

public interface GenericService<T extends AbstractEntity, ID> {
	List<T> findAll();
	Optional <T> findById (ID id); // cover T tránh bị null
	T save (T entity);
	T update (T entity);
	void deleteById(ID id);
	

}
