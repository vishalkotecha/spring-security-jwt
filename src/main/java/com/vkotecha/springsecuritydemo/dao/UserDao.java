package com.vkotecha.springsecuritydemo.dao;

import com.vkotecha.springsecuritydemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vishal Kotecha
 */
public interface UserDao extends JpaRepository<User,Long> {

  User findByUserName(String userName);

}
