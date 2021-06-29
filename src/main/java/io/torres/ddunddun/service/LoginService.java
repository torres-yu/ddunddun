package io.torres.ddunddun.service;

import io.torres.ddunddun.entity.Member;
import io.torres.ddunddun.exception.EmptyDataException;
import io.torres.ddunddun.repository.MemberRepository;
import io.torres.ddunddun.security.JwtTokenProvider;
import io.torres.ddunddun.util.VoEntityConverter;
import io.torres.ddunddun.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EmptyStackException;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    //생성자 주입
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    VoEntityConverter voEntityConverter = new VoEntityConverter();

    @Transactional
    public Long join(User user) {

        Member member = new Member();
        voEntityConverter.voToEntityConvert(user, member);
        //패스워드 암호화
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member).getSeq();
    }

    @Transactional
    public String login(User user) {

        Member member = memberRepository.findByEmailId(user.getEmaiId()).orElse(null);
        if(member==null) return "no employee "+user.getEmaiId();

        if(!passwordEncoder.matches(user.getPassword(), member.getPassword())) return "password Error";

        //로그인 성공했으면 토큰 발급
        return jwtTokenProvider.createToken(user, "access");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmailId(username).orElseThrow(() -> new EmptyDataException("no member"));
    }
}
