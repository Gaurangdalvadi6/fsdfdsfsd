package com.rental.controller;

import com.rental.dto.SignupRequest;
import com.rental.dto.UserDto;
import com.rental.repository.UserRepository;
import com.rental.service.auth.AuthService;
import com.rental.service.jwt.UserService;
import com.rental.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final JWTUtil jwtUtil;

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
        if (authService.hasCustomerWithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("Customer already exist with this email",HttpStatus.NOT_ACCEPTABLE);
        UserDto createdCustomerDto = authService.createCustomer(signupRequest);
        if (createdCustomerDto == null){
            return new ResponseEntity<>("Customer not created, Come again later", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdCustomerDto,HttpStatus.CREATED);
    }
}
