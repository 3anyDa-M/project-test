package com.project.test.tech_serv.service.bisinessService;

import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import org.springframework.web.bind.annotation.RequestParam;

public interface EmployeBusinessService {
    GetFullDataEmployeeResponse  getFullDataEmployeeResponse(@RequestParam String lastName,boolean kafkaSend);

}
