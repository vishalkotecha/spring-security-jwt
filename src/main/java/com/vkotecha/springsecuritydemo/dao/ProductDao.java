package com.vkotecha.springsecuritydemo.dao;

import com.vkotecha.springsecuritydemo.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vishal Kotecha
 */
public interface ProductDao extends JpaRepository<Products,Long> {
}
