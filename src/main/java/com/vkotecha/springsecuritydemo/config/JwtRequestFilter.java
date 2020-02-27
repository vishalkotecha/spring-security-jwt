package com.vkotecha.springsecuritydemo.config;

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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vkotecha.springsecuritydemo.util.JwtUtil;

/**
 * @author Vishal Kotecha
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private static final Logger LOG = LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private DomainUserDetailService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		LOG.info("Inside JWT reqeust filter ...");

		final String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader == null || authorizationHeader.trim().isEmpty()
				|| !authorizationHeader.startsWith("Bearer ")) {
			LOG.warn("No valid header available");
			filterChain.doFilter(request, response);
			return;
		}
		
		if(SecurityContextHolder.getContext().getAuthentication() != null) {
			LOG.warn("Authentiation already available.. no action to be performed");
			filterChain.doFilter(request, response);
			return;
		}

		String jwt = authorizationHeader.substring(7);
		
		String userName = jwtUtil.extractUserName(jwt);
		
		UserDetails userDetails = userDetailService.loadUserByUsername(userName);
		
		if(jwtUtil.isValidToken(jwt, userDetails)) {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		
		filterChain.doFilter(request, response);

	}

}
