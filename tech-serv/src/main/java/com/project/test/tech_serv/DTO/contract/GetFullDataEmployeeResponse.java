package com.project.test.tech_serv.DTO.contract;

import com.project.test.tech_serv.DTO.entity.EmployeeDTO;
import com.project.test.tech_serv.DTO.entity.PasportDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record GetFullDataEmployeeResponse(List <EmployeeDTO> employeeDTO,
                                          List<PasportDTO> pasportDTO) {

    public static final GetFullDataEmployeeResponse EMPTY = new GetFullDataEmployeeResponse(null, null);
}
