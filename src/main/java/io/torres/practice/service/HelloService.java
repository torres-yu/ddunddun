package io.torres.practice.service;

import io.torres.practice.entity.Employee;
import io.torres.practice.repository.EmployeeRepository;
import io.torres.practice.util.VoEntityConverter;
import io.torres.practice.vo.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HelloService {

    @Autowired
    private EmployeeRepository employeeRepository;

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
