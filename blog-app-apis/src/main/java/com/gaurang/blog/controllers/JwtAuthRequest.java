package com.gaurang.blog.controllers;

import lombok.Data;

@Data
public class JwtAuthRequest {

	// ahi apne email nej username tarikhe lidho chhe je User entity ma set karel chhe
	private String username;
	
	private String password;
	
}
