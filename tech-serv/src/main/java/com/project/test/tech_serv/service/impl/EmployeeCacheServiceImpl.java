package com.project.test.tech_serv.service.impl;

import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import com.project.test.tech_serv.DTO.entity.EmployeeDTO;
import com.project.test.tech_serv.DTO.entity.PasportDTO;
import com.project.test.tech_serv.service.EmployeeCacheService;
import com.project.test.tech_serv.service.EmployeeService;
import com.project.test.tech_serv.service.PasportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Log4j2
@Service
@RequiredArgsConstructor
public class EmployeeCacheServiceImpl implements EmployeeCacheService {
    private final EmployeeService employeeService;
    private final PasportService pasportService;


    @Cacheable(value = "employee_full", key = "#firstName")
    public GetFullDataEmployeeResponse getEmployeeData(String firstName) {
        log.debug("⚠️Получаем данные из БД для {}", firstName);

        List<EmployeeDTO> employeeDTO = employeeService.findByFirstName(firstName);
        List<PasportDTO> pasportDTO = pasportService.findByFirstName(firstName);


//        GetFullDataEmployeeResponse response =employeeService.getFullfindByFirstName(firstName);

        if (employeeDTO == null || pasportDTO == null) {
            return GetFullDataEmployeeResponse.EMPTY;
        }
        return new GetFullDataEmployeeResponse(employeeDTO, pasportDTO);
//        return response;
    }
}

