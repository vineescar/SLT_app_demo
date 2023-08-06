package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// check customer

	@PostMapping("/employees/login")
	public ResponseEntity<?> loginEmployee(@RequestBody Map<String, String> loginData) {
	    String user = loginData.get("user");
	    String pwd = loginData.get("pwd");

	    System.out.println("User: " + user + ", Password: " + pwd);

	    long phoneNo;
	    try {
	        phoneNo = Long.parseLong(user);
	        System.out.println(phoneNo);
	    } catch (NumberFormatException e) {
	        return ResponseEntity.badRequest().body("Invalid phone number format");
	    }

	    try {
	        Employee employee = employeeRepository.findById(phoneNo)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + phoneNo));
	        
	        System.out.println(employee);

	        if (!employee.getPassword().equals(pwd)) {
	            // Invalid password, return a response with the desired message
	            return ResponseEntity.ok("Password is incorrect");
	        }

	        // Employee found and password is correct, return the employee details
	        return ResponseEntity.ok(employee);
	    } catch (ResourceNotFoundException e) {
	        // Employee not found, return a response with the desired message
	        return ResponseEntity.ok("Employee not exist with id :" + phoneNo);
	    }
	}
	
	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
	    // Assuming you have setters for the remainingData and packageName properties in the Employee class
		
	    // Generate a random number between 0 and 1
	    double randomValue = Math.random();

	    // Set the value of remainingData based on the random number
	    if (randomValue < 0.5) {
	        // If randomValue is less than 0.5, set remainingData as webfamily value
	        employee.setPackageName("webfamily");
	    } else {
	        // If randomValue is greater than or equal to 0.5, set remainingData as webstarter value
	        employee.setPackageName("webstarter");
	    }

	    if ("webfamily".equals(employee.getPackageName())) {
	        // If the package name is "webfamily," set remainingData as a random value between 10 and 90
	        int randomRemainingData = (int) (randomValue * 81) + 10; // Generates a random integer between 10 and 90
	        employee.setRemainingData(String.valueOf(randomRemainingData));
	    } else if ("webstarter".equals(employee.getPackageName())) {
	        // If the package name is "webstarter," set remainingData as a random value between 10 and 60
	        int randomRemainingData = (int) (randomValue * 51) + 10; // Generates a random integer between 10 and 60
	        employee.setRemainingData(String.valueOf(randomRemainingData));
	    } else {
	        // Handle other package names here if needed
	    }
	    
	    System.out.println(employee);

	    return employeeRepository.save(employee);
	}

}


	 

	

	
//	// get employee by id rest api
//	@GetMapping("/employees/{id}")
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
//		return ResponseEntity.ok(employee);
//	}
//	
//	// update employee rest api
//	
//	@PutMapping("/employees/{id}")
//	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
//		
//		employee.setFirstName(employeeDetails.getFirstName());
//		employee.setEmailId(employeeDetails.getEmailId());
//		
//		Employee updatedEmployee = employeeRepository.save(employee);
//		return ResponseEntity.ok(updatedEmployee);
//	}
//	
//	// delete employee rest api
//	@DeleteMapping("/employees/{id}")
//	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
//		
//		employeeRepository.delete(employee);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return ResponseEntity.ok(response);
//	}
	
	
