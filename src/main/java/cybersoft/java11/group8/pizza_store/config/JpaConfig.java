package cybersoft.java11.group8.pizza_store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import cybersoft.java11.group8.pizza_store.common_data.AuditorAwareImpl;



@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {
	@Bean
	AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}
	
	@Component
	public interface GenericRepository <T,ID> extends JpaRepository<T, ID>{

	}
}
