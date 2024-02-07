package com.robertocosta.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robertocosta.dscommerce.entities.OrderItem;
import com.robertocosta.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
