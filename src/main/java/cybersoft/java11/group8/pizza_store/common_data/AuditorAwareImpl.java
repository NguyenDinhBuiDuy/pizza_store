package cybersoft.java11.group8.pizza_store.common_data;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		SecurityContext currentSecurityContext = SecurityContextHolder.getContext();
		String currentUsername = currentSecurityContext.getAuthentication().getName();
		return Optional.ofNullable(currentUsername);
	}

}
