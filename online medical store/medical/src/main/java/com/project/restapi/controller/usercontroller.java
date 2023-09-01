package com.project.restapi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.restapi.model.User;
import com.project.restapi.service.UserService;



@RestController
@RequestMapping("/api/v1/user")
public class usercontroller {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getUser")
	public ResponseEntity<List<User>>getUser(){
		return ResponseEntity.status(200).body(userService.getUser());
	}
	
	@GetMapping("/getQuery")
	public ResponseEntity<List<User>> getQuery(){
		return ResponseEntity.status(200).body(userService.findAllQuery());
	}
	
	@GetMapping("/getEmail/{email}")
	public ResponseEntity<User> getEmail(@PathVariable String email){
		return ResponseEntity.status(200).body(userService.getEmail(email));
		
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<Page<User>> getAllUser(
				@RequestParam(defaultValue = "0") int page, 
				@RequestParam(defaultValue = "10") int size,
				@RequestParam(defaultValue = "id") String sortField,
				@RequestParam(defaultValue = "asc") String sortOrder
			){
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortField));
		return ResponseEntity.status(200).body(userService.getAllUser(pageRequest));
	}
	
	
	
	@PostMapping("/addUser")
	public ResponseEntity<String>addUser(@RequestBody User user){
		boolean dataSaved= userService.adduser(user);
		if(dataSaved) {
			return ResponseEntity.status(200).body("User added successfully");
		}
		else {
			return ResponseEntity.status(404).body("Something went wrong");
		}
		
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user){
		boolean userData = userService.updateuser(id,user);
		if(userData) {
			return ResponseEntity.status(200).body("User updated successfully");
		} else{
			return ResponseEntity.status(404).body("No record found to be updated");
		}
	}
	
	@PutMapping("/updateUserQuery/{name}/{id}")
	public ResponseEntity<String> updateUserQuery(@PathVariable String name, @PathVariable Long id){
		int flag = userService.updateUserQuery(name, id);
		if(flag == 1)
			return ResponseEntity.status(200).body("Record updated successfully!");
		else
			return ResponseEntity.status(200).body("Something went wrong!");
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		boolean userDeleted = userService.deleteUser(id);
		if(userDeleted) {
			return ResponseEntity.status(200).body("User deleted successfully");
		} else {
			return ResponseEntity.status(404).body("No record found to be updated");
		}
	}
	
	/*@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@RequestParam Long id){
		boolean userDeleted = userService.deleteUser(id);
		if(userDeleted) {
			return ResponseEntity.status(200).body("User deleted successfully");
		} else {
			return ResponseEntity.status(404).body("No record found to be updated");
		}
	}*/
	@DeleteMapping("/deleteUserQuery/{id}")
	public ResponseEntity<String> deleteUserQuery(@PathVariable Long id) {
		int flag = userService.deleteUserQuery(id);
		if(flag == 1)
			return ResponseEntity.status(200).body("Record deleted successfully!");
		else
			return ResponseEntity.status(200).body("Something went wrong!");
	}
}
