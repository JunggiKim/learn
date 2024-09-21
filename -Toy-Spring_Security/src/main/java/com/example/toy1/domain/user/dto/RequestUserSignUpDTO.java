package com.example.toy1.domain.user.dto;

import org.springframework.beans.factory.annotation.Value;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Builder;

@Builder
public record RequestUserSignUpDTO(
	String email ,
	String password,
	String nickName,
	int age,
	String city
	) {


}
