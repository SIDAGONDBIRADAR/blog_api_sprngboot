package com.biradar.sidagond.services;

import java.util.List;

import com.biradar.sidagond.dto.UserDto;
import com.biradar.sidagond.model.User;

public interface UserService {

	public User createUser(UserDto userDto);

	public User updateUser(UserDto userDto, Integer userId) throws Exception;

	public User getUserById(Integer userId) throws Exception;

	public List<User> getAllUsers();

	public void deleteById(Integer userId) throws Exception;

}
