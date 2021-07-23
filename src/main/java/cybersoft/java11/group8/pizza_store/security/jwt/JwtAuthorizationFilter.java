package cybersoft.java11.group8.pizza_store.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = jwtUtils.getTokenFromRequest(request);
			
			if(token != null && jwtUtils.validateJwtToken(token)) {
				String username = jwtUtils.getUsernameFromToken(token);
				
				UserDetails userDetails = service.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authenticationToken 
					= new  UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		} catch (UsernameNotFoundException e) {
			logger.error("An anonymous user has been connected to server from {}", request.getRemoteAddr());
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
