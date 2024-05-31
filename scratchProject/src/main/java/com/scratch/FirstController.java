package com.scratch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

	//@GetMapping("/hello")
	public String sayHello() {
		return "Hello from my first controller";
	}
	
	@PostMapping("/post")
	public String post( @RequestBody String message) {
		return "request is accepted and message is : "+message;
	}
	
	@PostMapping("/post-order")
	public String post(@RequestBody Order order) {
		return "request accepted and order is : "+ order.toString();
	}
	
	@PostMapping("/post-order-record")
	public String postRecord(@RequestBody OrderRecord orderRecord) {
		return "request accepted and order is : "+ orderRecord.toString();
	}
	
	@GetMapping("/hello/{userName}")
	public String pathVar(@PathVariable String userName) {
		return "The name is : " +userName;
	}
	
	@GetMapping("/hello")
	public String pathVar(@RequestParam String userName,@RequestParam String lastName) {
		return "The name is : " +userName+" "+lastName;
	}
}
