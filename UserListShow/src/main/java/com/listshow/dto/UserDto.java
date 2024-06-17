package com.listshow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDto(

		Long id,

		@NotEmpty(message = "name should not be empty")
		@NotBlank
		@Size(min = 2,message = "name must required 2 character")
		String name,

		@NotEmpty
		@Email
		String email,

		@NotEmpty(message = "message should not be empty")
		@NotBlank
		@Size(min = 2)
		String message) {

}
