package com.example.toy1.domain.user.dto;

import com.example.toy1.domain.user.UserEntity;

import lombok.Builder;

@Builder
public record ResponseUserSignUpDTO(
	String email ,
	String nickname ,
	int age
) {
 public static ResponseUserSignUpDTO  of (UserEntity userEntity){
 return   ResponseUserSignUpDTO.builder()
	   .email(userEntity.getEmail())
	   .nickname(userEntity.getNickname())
	   .age(userEntity.getAge())
	   .build();
 }
}
