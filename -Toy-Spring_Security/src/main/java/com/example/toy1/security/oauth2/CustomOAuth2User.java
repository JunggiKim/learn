package com.example.toy1.security.oauth2;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.example.toy1.domain.user.Role;

import lombok.Getter;


//기존에  DefaultOAuth2User 를 사용해도 가능하지만
// 우리 서비스에 이메일과 유저의 회원등급을 따로 db에 저장을 하기위해 커스텀을 해서 필드를 추가한 클래스를 사용한다.
@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private String email;
    private Role role;



    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            String email, Role role) {
        super(authorities, attributes, nameAttributeKey);
        this.email = email;
        this.role = role;
    }
}