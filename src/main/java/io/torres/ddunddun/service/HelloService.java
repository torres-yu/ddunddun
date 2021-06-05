package io.torres.ddunddun.service;

import io.torres.ddunddun.entity.Employee;
import io.torres.ddunddun.repository.EmployeeRepository;
import io.torres.ddunddun.util.VoEntityConverter;
import io.torres.ddunddun.vo.response.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;

@Service
public class HelloService {

    @Autowired
    private EmployeeRepository employeeRepository;

    VoEntityConverter voEntityConverter = new VoEntityConverter();

    //기본 엔티티 리턴
    @Transactional
    public EmployeeVo getEmployee(){

        Employee employee = employeeRepository.findById(327L).get();
        EmployeeVo employeeVo = new EmployeeVo();
        voEntityConverter.entityToVoConvert(employee, employeeVo);

        return employeeVo;
    }
}
