package com.project.test.tech_serv.service;

import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import org.springframework.stereotype.Service;

@Service

public interface EmployeeCacheService {

    GetFullDataEmployeeResponse getEmployeeData(String firstName);
}
