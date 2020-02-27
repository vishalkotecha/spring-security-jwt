package com.vkotecha.springsecuritydemo.service;

import com.vkotecha.springsecuritydemo.dao.ProductDao;
import com.vkotecha.springsecuritydemo.model.Products;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Vishal Kotecha
 */
@Service
public class ProductService {

  private final ProductDao productRepository;

  public ProductService(ProductDao productRepository) {
    this.productRepository = productRepository;
  }

  public List<Products> getAll() {
    return productRepository.findAll();
  }
}
