package com.biradar.sidagond.services;

import java.util.List;

import com.biradar.sidagond.dto.UserDto;
import com.biradar.sidagond.model.User;

public interface UserService {
	
	public User createUser(UserDto userDto);
	public User updateUser(UserDto userDto);
	public User getUserById(Integer userId);
	public List<User> getAllUsers();
	public void deleteById(Integer userId);

}
