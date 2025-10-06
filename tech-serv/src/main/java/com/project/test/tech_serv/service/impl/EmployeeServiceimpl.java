package com.project.test.tech_serv.service.impl;

import com.project.test.tech_serv.DTO.entity.EmployeeDTO;
import com.project.test.tech_serv.repository.EmployeeRepository;
import com.project.test.tech_serv.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceimpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }
}
