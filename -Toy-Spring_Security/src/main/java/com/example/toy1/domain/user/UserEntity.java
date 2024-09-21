package com.example.toy1.domain.user;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.toy1.domain.BaseTimeEntity;
import com.example.toy1.domain.user.dto.RequestUserSignUpDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "USERS")
public class UserEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String email; // 이메일
	private String password; // 비밀번호
	private String nickname; // 닉네임
	private String imageUrl; // 프로필 이미지
	private int age;
	private String city; // 사는 도시

	@Enumerated(EnumType.STRING)
	private Role role;

	// 이 소셜타입이라는 것을 로그인타입으로 변경을 하자  일반로그인도 존재하기떄문에
	@Enumerated(EnumType.STRING)
	private SocialType socialType; // KAKAO, NAVER, GOOGLE

	private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

	private String refreshToken; // 리프레시 토큰

	// 유저 권한 설정 메소드
	public void authorizeUser() {
		this.role = Role.USER;
	}


	// 비밀번호 암호화 메소드
	public void passwordEncode(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}

	public void updateRefreshToken(String updateRefreshToken) {
		this.refreshToken = updateRefreshToken;
	}


    public static UserEntity of(RequestUserSignUpDTO requestUserSignUpDto) {
		return UserEntity.builder()
				.email(requestUserSignUpDto.email())
				.password(requestUserSignUpDto.password())
				.nickname(requestUserSignUpDto.nickName())
				.age(requestUserSignUpDto.age())
				.city(requestUserSignUpDto.city())
				.role(Role.USER)
				.build();
    }


}