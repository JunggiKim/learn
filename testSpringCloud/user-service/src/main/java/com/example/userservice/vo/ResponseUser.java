package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //반환 값이 널이라면 보여지지 않게하는 어노테이션
public class ResponseUser {
	private String email;
	private String name;
	private String userId;

	private List<ResponseOrder> orders;
}