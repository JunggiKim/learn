package com.example.toy1.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	// 스프링 시큐리티에서 "ROLE" 로 시작을하지 않으면 익셉션이 발생하기 때문에 넣어주어야 한다.
	GUEST("ROLE_GUEST"),
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");

	private final String text;

}
