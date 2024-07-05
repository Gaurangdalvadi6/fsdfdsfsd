package com.graphql.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.graphql.entities.Order;
import com.graphql.exception.ExceptionHandler;
import com.graphql.repositories.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}
	
	// create order
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}
	
	// get All order
	public List<Order> getAllOrder(){
		return orderRepository.findAll();
	}
	
	// get order
	public Order getOrder(Integer orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(ExceptionHandler::throwResourceNotFoundException);
		return order;
	}
	
	// update order
	public Order updateOrder(Order order,Integer orderId) {
		Order order2 = orderRepository.findById(orderId).orElseThrow(ExceptionHandler::throwResourceNotFoundException);
		order2.setPrice(order.getPrice());
		order2.setAddress(order.getAddress());
		order2.setOrderDetail(order.getOrderDetail());
		Order save = orderRepository.save(order2);
		return save;
		
	}
	
	// delete Order
	public boolean deleteOrder(Integer orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(ExceptionHandler::throwResourceNotFoundException);
		orderRepository.delete(order);
		return true;
		
	}
}
