package com.biradar.sidagond.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biradar.sidagond.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserId(Integer userId);

}
