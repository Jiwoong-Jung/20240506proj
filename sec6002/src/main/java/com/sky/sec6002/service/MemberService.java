package com.sky.sec6002.service;

import com.sky.sec6002.member.MemberJoinRequestDto;
import com.sky.sec6002.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String addUser(MemberJoinRequestDto requestDto) {
//        if (memberRepository.findByUsername(requestDto.getUsername()).isPresent()) {
//            throw new IllegalArgumentException("Username already exists");
//        }
//        return memberRepository.save(requestDto.toEntity(passwordEncoder)).getUsername();
         return memberRepository.save(requestDto.toEntity(passwordEncoder)).getUsername();
    }


}