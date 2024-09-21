package com.example.toy1.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.toy1.domain.user.repository.UserRepository;
import com.example.toy1.domain.user.service.UserService;

@SpringBootTest
public class LoginTest {


	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;






	@BeforeEach
	// void setUp() {
	// 	userRepository.save( )
	// }

	@DisplayName("기본 로그인을 한다")
	@Test

	void testName() throws Exception{
	    //Given



	    //When

	    //Then
	}
}
