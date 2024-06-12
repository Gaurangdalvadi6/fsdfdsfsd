package com.scratch.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record StudentDto(
		@NotEmpty(message = "firstname should not be empty")
		@Size(min = 3)
		String firstName,

		@NotEmpty(message = "lastname should not be empty")
		@Size(min = 3)
		String lastName,

		@NotEmpty
		@Email
		String email,

		Integer schoolId) {

}
