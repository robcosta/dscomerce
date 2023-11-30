package com.robertocosta.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robertocosta.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
