package com.graphql.controller;

import com.graphql.entities.Order;
import com.graphql.entities.User;
import com.graphql.service.OrderService;
import com.graphql.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {

    private final UserService userService;

    private  final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    // create ordre
    @MutationMapping
    public Order createOrder(
        @Argument String orderDetail,
        @Argument String address,
        @Argument int price,
        @Argument int userId
    ){
        User user = userService.getUser(userId);
        Order order = new Order();
        order.setOrderDetail(orderDetail);
        order.setAddress(address);
        order.setPrice(price);
        order.setUser(user);

        Order order1 = orderService.createOrder(order);
        return  order1;
    }

    // get orders
    @QueryMapping
    public List<Order> getAllOrder(){
        return  orderService.getAllOrder();
    }

    // get order
    @QueryMapping
    public Order getOrder(@Argument int orderId){
        return orderService.getOrder(orderId);
    }


    @MutationMapping
    public boolean deleteOrder(@Argument int orderId){
        return  orderService.deleteOrder(orderId);
    }
}
