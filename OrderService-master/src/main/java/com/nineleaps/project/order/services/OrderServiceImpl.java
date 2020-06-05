package com.nineleaps.project.order.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nineleaps.project.order.entities.Order;
import com.nineleaps.project.order.exceptions.OrderNotFoundException;
import com.nineleaps.project.order.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository repository;

	@Override
	public List<Order> getAllOrders() {
		return repository.findAll();
	}

	@Override
	public Optional<Order> getOrder(UUID id) throws OrderNotFoundException {
		Optional<Order> order = repository.findById(id);
		if (!order.isPresent())
			throw new OrderNotFoundException("Order doesn't exist.");

		return order;
	}

	@Override
	public Order addOrder(Order order) {
		
		return repository.save(order);
	}

}
