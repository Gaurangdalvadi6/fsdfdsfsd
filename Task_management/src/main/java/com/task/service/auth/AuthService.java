package com.task.service.auth;

import com.task.dto.SignUpRequest;
import com.task.dto.UserDto;

public interface AuthService {

    UserDto signUpUser(SignUpRequest signUpRequest);

    boolean hasUserWithEmail(String email);
}
