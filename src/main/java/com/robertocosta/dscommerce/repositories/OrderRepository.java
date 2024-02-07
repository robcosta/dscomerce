package com.robertocosta.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robertocosta.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
