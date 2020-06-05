package com.nineleaps.project.order.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.nineleaps.project.order.entities.Order;
import com.nineleaps.project.order.entities.PublishMessageModel;
import com.nineleaps.project.order.exceptions.OrderNotFoundException;
import com.nineleaps.project.order.services.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Orders Management RESTful Services", value = "Orders Service", description = "Controller for Orders Management Service")
public class OrderController {

	private final OrderService service;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	public OrderController(OrderService service) {
		this.service = service;		
	}

	@ApiOperation(value = "Get All Orders", response = Order.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),

	})
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return service.getAllOrders();
	}

	@ApiOperation(value = "Get order by id", response = Order.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Order"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),

	})
	@GetMapping("/orders/{id}")
	public Optional<Order> getOrder(@PathVariable("id") UUID id) {
		try {
			return service.getOrder(id);
		} catch (OrderNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@ApiOperation(value = "Create new Order", response = Order.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Created Order"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),

	})
	@PostMapping("/orders")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		
		Order orderPlaced = service.addOrder(order);
		String url = "http://localhost:8082/api/publish";
		Gson gson = new Gson();
		String orderString = gson.toJson(orderPlaced);
		System.out.println("orderString:"+orderString);
		
		PublishMessageModel obj2 = new PublishMessageModel();
		obj2.setTopicName("SupplierTopic");
		obj2.setMessage(orderString);
		System.out.println("obj2::"+obj2.toString());
		//String ValueasJson = obj1.writeValueAsString("{\"TopicName\":\"SupplierTopic\",\"Message\":\""+orderString+"\"}");		
		//System.out.println("ValueasJson : "+ ValueasJson);
		
		PublishMessageModel response = restTemplate.postForObject(url, obj2, PublishMessageModel.class);
		System.out.println("response : "+ response);
		return new ResponseEntity<Order>(orderPlaced, HttpStatus.CREATED);
	}

}
