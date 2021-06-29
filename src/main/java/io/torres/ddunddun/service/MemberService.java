package io.torres.ddunddun.service;

import io.torres.ddunddun.code.Role;
import io.torres.ddunddun.entity.Member;
import io.torres.ddunddun.repository.MemberRepository;
import io.torres.ddunddun.util.VoEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    /*
    @Autowired
    private MemberRepository memberRepository;

    VoEntityConverter voEntityConverter = new VoEntityConverter();

    //회원가입
    @Transactional
    public Long signUp(MemberVo memberVo) {


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberVo.setPassword(passwordEncoder.encode(memberVo.getPassword()));

        Member member = new Member();
        voEntityConverter.voToEntityConvert(memberVo, member);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> memberWrapper = memberRepository.findByUserName(username);
        Member member = memberWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        //

        return new User(member.getUserName(), member.getPassword(), authorities);
    }
    */
}
