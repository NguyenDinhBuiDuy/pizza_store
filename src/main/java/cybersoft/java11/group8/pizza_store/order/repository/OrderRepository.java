package cybersoft.java11.group8.pizza_store.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java11.group8.pizza_store.order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
