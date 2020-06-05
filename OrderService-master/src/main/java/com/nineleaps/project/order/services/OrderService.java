package com.nineleaps.project.order.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nineleaps.project.order.entities.Order;
import com.nineleaps.project.order.exceptions.OrderNotFoundException;

@Service
public interface OrderService {

	List<Order> getAllOrders();

	Optional<Order> getOrder(UUID id) throws OrderNotFoundException;

	Order addOrder(Order order);
}
