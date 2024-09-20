package io.dodn.springboot.core.domain.member;

import io.dodn.springboot.core.enums.MemberRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member {

    private String email;

    private String password;

    private String nickname;

    private String imageUrl;

    private int age;

    private String city;

    private MemberRole memberRole;

    // 유저 권한 설정 메소드
    // public void authorizeUser() {
    // this.role = Role.USER;
    // }

    // 비밀번호 암호화 메소드
    // public void passwordEncode(PasswordEncoder passwordEncoder) {
    // this.password = passwordEncoder.encode(this.password);
    // }

}