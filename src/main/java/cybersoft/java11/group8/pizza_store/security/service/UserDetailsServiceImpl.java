package cybersoft.java11.group8.pizza_store.security.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.role.model.RoleGroup;
import cybersoft.java11.group8.pizza_store.security.dto.UserDetailsDTO;
import cybersoft.java11.group8.pizza_store.user.model.User;
import cybersoft.java11.group8.pizza_store.user.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByUsername(username);
		
		if(user.isEmpty())
			throw new UsernameNotFoundException("Username is invalid.");
		
		Set<GrantedAuthority> authorities = getAuthorities(user.get().getRoleGroups());
		
		
		return new UserDetailsDTO(user.get().getUsername(), user.get().getPassword(), authorities);
	}

	private Set<GrantedAuthority> getAuthorities(Set<RoleGroup> roleGroups) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		Iterator<RoleGroup> iterator = roleGroups.iterator();
		
		while(iterator.hasNext()) 
			authorities.add(new SimpleGrantedAuthority(iterator.next().getRoleGroupName()));
			
		return authorities;
	}
}
