package com.project.test.tech_serv.service;

import com.project.test.tech_serv.DTO.entity.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO findByFirstName(String firstName);
}
