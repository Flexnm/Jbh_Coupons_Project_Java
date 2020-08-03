package com.example.couponsProject.web;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.couponsProject.exceptions.LoginFailedException;
import com.example.couponsProject.facade.ClientFacade;
import com.example.couponsProject.loginManager.ClientType;
import com.example.couponsProject.loginManager.LoginManager;
import com.example.couponsProject.loginManager.Session;

@RestController
@CrossOrigin("http://localhost:4200")
public class LoginController {

	private LoginManager manager;
	@Autowired
	private Map<String, Session> sessionMap;

	public LoginController(LoginManager manager) {
		this.manager = manager;
	}

	@PostMapping("login/{email}/{password}/{type}")
	public ResponseEntity<String> login(@PathVariable String email, @PathVariable String password,
			@PathVariable String type) {
			try {
				ClientFacade client = manager.login(email, password, ClientType.valueOf(type));
				String token = UUID.randomUUID().toString();
				sessionMap.put(token, new Session(client, System.currentTimeMillis()));
				return ResponseEntity.ok(token);
			} catch (LoginFailedException e) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
			}
	}

	@PostMapping("logout/{token}")
	public ResponseEntity<?> logout(@PathVariable String token) {
		sessionMap.remove(token);
		return ResponseEntity.ok(token);
	}
}
