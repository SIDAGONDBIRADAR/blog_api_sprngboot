package com.biradar.sidagond;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biradar.sidagond.repository.UserRepository;

@SpringBootTest
class BlogApplicationTests {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		System.out.println((this.userRepository.getClass().getName()));
	}

}
