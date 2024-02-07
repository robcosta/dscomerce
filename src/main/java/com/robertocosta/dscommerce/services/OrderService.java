package com.robertocosta.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.robertocosta.dscommerce.dto.OrderDTO;
import com.robertocosta.dscommerce.dto.OrderItemDTO;
import com.robertocosta.dscommerce.entities.Order;
import com.robertocosta.dscommerce.entities.OrderItem;
import com.robertocosta.dscommerce.entities.OrderStatus;
import com.robertocosta.dscommerce.entities.Product;
import com.robertocosta.dscommerce.repositories.OrderItemRepository;
import com.robertocosta.dscommerce.repositories.OrderRepository;
import com.robertocosta.dscommerce.repositories.ProductRepository;
import com.robertocosta.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
		return new OrderDTO(order);
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order();
		
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		
		order.setClient(userService.authenticated());
		
		for(OrderItemDTO itemDto : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
			order.getItems().add(item);
		}
		
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		return new OrderDTO(order);
	}
}
