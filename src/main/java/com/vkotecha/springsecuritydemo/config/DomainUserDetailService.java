package com.vkotecha.springsecuritydemo.config;

import com.vkotecha.springsecuritydemo.dao.UserDao;
import com.vkotecha.springsecuritydemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Vishal Kotecha
 */
@Service
public class DomainUserDetailService implements UserDetailsService {

  @Autowired
  private UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.findByUserName(username);
    if(user == null) throw new UsernameNotFoundException("User not found!");
    return new DomainUserDetails(user);
  }
}
