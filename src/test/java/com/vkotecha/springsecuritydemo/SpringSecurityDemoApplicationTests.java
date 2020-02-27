package com.vkotecha.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityDemoApplicationTests {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
  @Test
  void contextLoads() {
	  String encode = passwordEncoder.encode("test");
	  System.out.println(encode);
	  
  }

}
