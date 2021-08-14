package io.torres.practice.service;

import org.springframework.stereotype.Service;

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
