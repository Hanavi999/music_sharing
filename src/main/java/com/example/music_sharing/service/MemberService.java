package com.example.music_sharing.service;

import com.example.music_sharing.adapter.MemberAdapter;
import com.example.music_sharing.entity.Member;
import com.example.music_sharing.entity.Role;
import com.example.music_sharing.entity.dto.MemberDto;
import com.example.music_sharing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> userOpt = memberRepository.findByUsername(username);

        if(userOpt.isPresent()) {
            log.info("USER FOUND IN THE DATABASE: {}", username);
            return new MemberAdapter(userOpt.get());
        }

        throw new UsernameNotFoundException("존재하지 않는 사용자 입니다.");
    }

    public void createAccount(MemberDto memberDto) {
        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .nickname(memberDto.getNickname())
                .role(Role.USER.getKey()).build();

        memberRepository.save(member);
    }



}
