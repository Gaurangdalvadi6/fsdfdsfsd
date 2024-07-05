package com.graphql.controller;

import com.graphql.entities.User;
import com.graphql.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // create user api
    @MutationMapping
    public User createUser(@Argument String name,@Argument String phone,@Argument String email,@Argument String password){
        User user= new User();
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        return  userService.create(user);
    }

    // get users
    @QueryMapping
    public List<User> getAllUser(){
        return  userService.getAllUser();
    }

    // get single user
    @QueryMapping
    public  User getUser(@Argument int userId){
        return  userService.getUser(userId);
    }

    // delete user
    @MutationMapping
    public  boolean deleteUser(@Argument int userId){
        return  userService.deleteUser(userId);
    }
}
