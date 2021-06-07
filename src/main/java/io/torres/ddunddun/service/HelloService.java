package io.torres.ddunddun.service;

import io.torres.ddunddun.entity.Employee;
import io.torres.ddunddun.repository.EmployeeRepository;
import io.torres.ddunddun.security.TokenProvider;
import io.torres.ddunddun.util.VoEntityConverter;
import io.torres.ddunddun.vo.EmployeeVo;
import io.torres.ddunddun.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HelloService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RefreshTokenService refreshTokenService;

    VoEntityConverter voEntityConverter = new VoEntityConverter();

    //기본 엔티티 리턴
    @Transactional
    public EmployeeVo getEmployee() {

        Employee employee = employeeRepository.findById(327L).get();
        EmployeeVo employeeVo = new EmployeeVo();
        voEntityConverter.entityToVoConvert(employee, employeeVo);

        return employeeVo;
    }

    //jwt토큰 생성, redis에 저장
    public void getToken() {

        Employee employee = employeeRepository.findById(327L).get();
        User user = new User();
        voEntityConverter.entityToVoConvert(employee, user);

        String accessToken = tokenProvider.issueToken(user, "Access");
        String refreshToken = tokenProvider.issueToken(user, "refresh");
        refreshTokenService.save(user,refreshToken);
    }

}
