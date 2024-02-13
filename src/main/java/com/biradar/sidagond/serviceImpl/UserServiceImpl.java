package com.biradar.sidagond.serviceImpl;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biradar.sidagond.dto.UserDto;
import com.biradar.sidagond.exceptions.Invalidparameter;
import com.biradar.sidagond.exceptions.UserNotFoundException;
import com.biradar.sidagond.model.User;
import com.biradar.sidagond.repository.UserRepository;
import com.biradar.sidagond.services.UserService;

import io.micrometer.common.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public User createUser(UserDto userDto) {
		User newUser = new User();
		newUser.setName(userDto.getName());
		newUser.setEmail(userDto.getEmail());
		newUser.setPassword(userDto.getPassword());
		newUser.setDesignation(userDto.getDesignation());
		User savedUser = userRepository.save(newUser);
		return savedUser;
	}

	@Override
	public User updateUser(UserDto userDto, Integer userId) throws Exception {
		User databaseUser = userRepository.findByUserId(userId);
		if (databaseUser != null) {
			databaseUser.setName(userDto.getName());
			databaseUser.setEmail(userDto.getEmail());
			databaseUser.setDesignation(userDto.getDesignation());
			databaseUser.setPassword(userDto.getPassword());
			return userRepository.save(databaseUser);
		} else {
			throw new UserNotFoundException("User not found with UserId : " + userId);
		}

	}

	@Override
	public User getUserById(Integer userId) throws Exception {
		logger.info("=> inside getUser method in userserviceimpl class{}",userId);
		if (userId == null && userId <= 0) {
			throw new Invalidparameter("User Id cannot be empty");
		}
		User databaseUser = userRepository.findByUserId(userId);
		if (databaseUser != null) {
			return databaseUser;
		} else {
			throw new UserNotFoundException("User not found with UserId : " + userId);
		}

	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public void deleteById(Integer userId) throws Exception {
		if (StringUtils.isBlank(String.valueOf(userId))) {
			throw new InvalidParameterException("User Id cannot be empty");
		}
		User databaseUser = userRepository.findByUserId(userId);
		if (databaseUser != null) {
			userRepository.delete(databaseUser);

		} else {
			throw new UserNotFoundException("User not found with UserId : " + userId);
		}

	}

}
