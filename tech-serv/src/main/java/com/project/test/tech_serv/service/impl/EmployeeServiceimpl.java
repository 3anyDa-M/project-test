package com.project.test.tech_serv.service.impl;

import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import com.project.test.tech_serv.DTO.entity.EmployeeDTO;
import com.project.test.tech_serv.model.Employee;
import com.project.test.tech_serv.repository.EmployeeRepository;
import com.project.test.tech_serv.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceimpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List <EmployeeDTO> findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    @Override
    @Cacheable(value = "employees",key = "#id")
    public Employee getEmployeeById(long id) {
        System.out.println(" Запрос к БД для id=" + id);
        return employeeRepository.findById(id);
    }

    @Override
    public GetFullDataEmployeeResponse getFullfindByFirstName(String firstName) {
        return employeeRepository.getFullfindByFirstName(firstName);
    }


}
