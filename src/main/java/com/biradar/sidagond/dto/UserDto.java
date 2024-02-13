package com.biradar.sidagond.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

	@NotEmpty(message = "Name should have valid value")
	@Size(min = 3, max = 50, message = "Name should have atleast 3 characters")
	private String name;

	@NotEmpty(message = "email should have a value")
	@Email(message = "Enter valid email address")
	private String email;

	@NotEmpty(message = "Password should not be empty")
	@Size(min = 6, max = 20, message = "password must be minimum of 6 characters & maximum of 20 characters")
	private String password;

	@NotEmpty(message = "Designation should have valid value")
	@Size(min = 4, max = 20, message = "please enter valid designation")
	private String designation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
