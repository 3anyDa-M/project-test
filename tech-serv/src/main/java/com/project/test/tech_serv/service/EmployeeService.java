package com.project.test.tech_serv.service;

import com.project.test.tech_serv.DTO.entity.EmployeeDTO;
import com.project.test.tech_serv.model.Employee;

import java.util.List;

public interface EmployeeService {

    List <EmployeeDTO> findByFirstName(String firstName);

    Employee getEmployeeById(long id);
}
