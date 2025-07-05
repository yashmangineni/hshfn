package com.coddinghub.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coddinghub.Service.serviceinterface;
import com.coddinghub.model.login;


@RestController
@CrossOrigin
public class Mycontrol {
	@Autowired
	serviceinterface service;
	@PostMapping("/insert")
	public ResponseEntity<?> save(@RequestBody login l) {
	    try {
	        login savedUser = service.save(l);
	        return ResponseEntity.ok(savedUser); // Return saved user if successful
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("❌ Error: Could not save user.");
	    }
	}
	@GetMapping("api/select")
	public List<login> getdata()
	{
		return service.getdata();
	}
	@GetMapping("sign/{email}/{password}")
	 public ResponseEntity<String> authenticateUser(@PathVariable String email, @PathVariable long password) {
	    boolean isAuthenticated = service.authenticateUser(email, password);
	    if (isAuthenticated) {
	        return ResponseEntity.ok("Login successful!");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
	    }
	}

}
