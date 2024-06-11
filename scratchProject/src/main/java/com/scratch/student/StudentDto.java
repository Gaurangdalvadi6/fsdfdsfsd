package com.scratch.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
		@NotEmpty(message = "please enter firstname ")
		String firstName,

		@NotEmpty(message = "please enter lastname ")
		String lastName,

		@NotEmpty
		@Email
		String email,

		Integer schoolId) {

}
