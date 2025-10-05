package com.project.test.tech_serv.controller;

import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import com.project.test.tech_serv.service.bisinessService.impl.EmployeeBusinessServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class EmployeeController {

    private final EmployeeBusinessServiceImpl employeeBusinessService;

    @GetMapping("/employee")
    public GetFullDataEmployeeResponse getFullDataEmployeeResponse(@RequestParam String firstName,
                                                                   @RequestParam boolean kafkaSend) {
        GetFullDataEmployeeResponse response =employeeBusinessService.getFullDataEmployeeResponse(firstName,kafkaSend);
        return  response;
    }

}
