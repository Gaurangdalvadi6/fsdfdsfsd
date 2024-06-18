package com.management.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.dto.ReqRes;
import com.management.entity.OurUsers;
import com.management.repository.UserRepo;

@Service
public class UsersManagementService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ReqRes register(ReqRes registrationRequest) {
		ReqRes resp = new ReqRes();
		try {
			OurUsers ourUsers = new OurUsers();
			ourUsers.setEmail(registrationRequest.getEmail());
			ourUsers.setCity(registrationRequest.getCity());
			ourUsers.setRole(registrationRequest.getRole());
			ourUsers.setName(registrationRequest.getName());
			ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
			OurUsers ourUsersResult = userRepo.save(ourUsers);
			if (ourUsersResult.getId() > 0) {
				resp.setOurUsers(ourUsersResult);
				resp.setMessage("User saved SuccessFully");
				resp.setStatusCode(200);
			}

		} catch (Exception e) {
			resp.setStatusCode(500);
			resp.setError(e.getMessage());
		}
		return resp;
	}

	public ReqRes login(ReqRes loginRequest) {
		ReqRes response = new ReqRes();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			var user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
			var jwt = jwtUtils.generateToken(user);
			var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
			response.setStatusCode(200);
			response.setToken(jwt);
			response.setRefreshToken(refreshToken);
			response.setExpirationTime("24Hrs");
			response.setMessage("Successfully Logged in");

		} catch (Exception e) {
			response.setStatusCode(500);
			response.setError(e.getMessage());
		}
		return response;
	}

	public ReqRes getAllUsers() {
		ReqRes reqRes = new ReqRes();

		try {
			List<OurUsers> result = userRepo.findAll();
			if (!result.isEmpty()) {
				reqRes.setOurUsersList(result);
				reqRes.setStatusCode(200);
				reqRes.setMessage("Successful");
			} else {
				reqRes.setStatusCode(404);
				reqRes.setMessage("No users found");
			}
		} catch (Exception e) {
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occured: " + e.getMessage());
		}
		return reqRes;
	}

	public ReqRes getUserById(Integer id) {
		ReqRes reqRes = new ReqRes();

		try {
			OurUsers userById = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
			reqRes.setOurUsers(userById);
			reqRes.setStatusCode(200);
			reqRes.setMessage("User with id : " + id + " found successfully");

		} catch (Exception e) {
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occured: " + e.getMessage());
		}
		return reqRes;
	}

	public ReqRes deleteUser(Integer userId) {
		ReqRes reqRes = new ReqRes();

		try {
			Optional<OurUsers> userOptional = userRepo.findById(userId);
			if (userOptional.isPresent()) {
				userRepo.deleteById(userId);
				reqRes.setStatusCode(200);
				reqRes.setMessage("User deleted Successfully");
			} else {
				reqRes.setStatusCode(404);
				reqRes.setMessage("User not found for deletion");
			}
		} catch (Exception e) {
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occured while deleting user: " + e.getMessage());
		}
		return reqRes;
	}

	public ReqRes updateUser(Integer userId, OurUsers updateUser) {
		ReqRes reqRes = new ReqRes();

		try {
			Optional<OurUsers> userOptional = userRepo.findById(userId);
			if (userOptional.isPresent()) {
				OurUsers existingUser = userOptional.get();
				existingUser.setEmail(updateUser.getEmail());
				existingUser.setCity(updateUser.getCity());
				existingUser.setName(updateUser.getName());
				existingUser.setRole(updateUser.getRole());

				// check if password is present in the request
				if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
					// encode the password and update it
					existingUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
				}

				OurUsers savedUser = userRepo.save(existingUser);
				reqRes.setOurUsers(savedUser);
				reqRes.setStatusCode(200);
				reqRes.setMessage("User Updated successFully");
			} else {
				reqRes.setStatusCode(404);
				reqRes.setMessage("User not found for update");
			}

		} catch (Exception e) {
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occured while updating user: " + e.getMessage());
		}
		return reqRes;
	}
	
	public ReqRes refreshToken(ReqRes refreshTokenReqiest) {
		ReqRes response = new ReqRes();
		try {
			String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
			OurUsers users = userRepo.findByEmail(ourEmail).orElseThrow();
			if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
				var jwt = jwtUtils.generateToken(users);
				response.setStatusCode(200);
				response.setToken(jwt);
				response.setRefreshToken(refreshTokenReqiest.getToken());
				response.setExpirationTime("24Hrs");
				response.setMessage("Successfully Refreshed Token");
			}
			response.setStatusCode(200);
			return response;
 
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage(e.getMessage());
			return response;
		}
	}

	public ReqRes getMyInfo(String email) {
		ReqRes reqRes = new ReqRes();
		try {
			Optional<OurUsers> userOptional = userRepo.findByEmail(email);
			if (userOptional.isPresent()) {
				reqRes.setOurUsers(userOptional.get());
				reqRes.setStatusCode(200);
				reqRes.setMessage("Successful");
			} else {
				reqRes.setStatusCode(404);
				reqRes.setMessage("User not found with email");
			}
		} catch (Exception e) {
			reqRes.setStatusCode(500);
			reqRes.setMessage("Error occured while getting user info: " + e.getMessage());
		}
		return reqRes;
	}
}
