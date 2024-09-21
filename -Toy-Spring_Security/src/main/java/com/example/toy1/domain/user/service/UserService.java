package com.example.toy1.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.toy1.domain.user.UserEntity;
import com.example.toy1.domain.user.dto.RequestUserSignUpDTO;
import com.example.toy1.domain.user.dto.ResponseUserSignUpDTO;
import com.example.toy1.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseUserSignUpDTO signUp(RequestUserSignUpDTO requestUserSignUpDto) throws Exception {

        signUpValuation(requestUserSignUpDto);

        UserEntity user = UserEntity.of(requestUserSignUpDto);

        user.passwordEncode(passwordEncoder);

        UserEntity save = userRepository.save(user);
        return ResponseUserSignUpDTO.of(save);
    }


    private void signUpValuation(RequestUserSignUpDTO requestUserSignUpDto) throws Exception {
        if (userRepository.findByEmail(requestUserSignUpDto.email()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        if (userRepository.findByNickname(requestUserSignUpDto.nickName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
    }

}
