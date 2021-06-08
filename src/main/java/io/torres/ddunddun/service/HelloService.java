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

        Employee employee = employeeRepository.findById(1L).get();
        EmployeeVo employeeVo = new EmployeeVo();
        voEntityConverter.entityToVoConvert(employee, employeeVo);

        return employeeVo;
    }
}
