package cybersoft.java11.group8.pizza_store.common_data;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;

public class GenericServiceImpl <T extends AbstractEntity,ID> implements GenericService<T, ID> {
	@Autowired
	private JpaRepository<T,ID> repository;
	
	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<T> findById(ID id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public T save(T entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	@Override
	public T update(T entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	@Override
	public void deleteById(ID id) {
		repository.deleteById(id);
		
	}

}
