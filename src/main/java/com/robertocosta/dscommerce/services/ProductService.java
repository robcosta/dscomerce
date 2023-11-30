package com.robertocosta.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.robertocosta.dscommerce.dto.ProductDTO;
import com.robertocosta.dscommerce.entities.Product;
import com.robertocosta.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
	//	Optional<Product> result = repository.findById(1L);
		Product product = repository.findById(id).get();
		return new ProductDTO(product);
	}

}
