package com.project.test.fileserv.model.Dto.contract;

import com.project.test.tech_serv.DTO.entity.EmployeeDTO;
import com.project.test.tech_serv.DTO.entity.PasportDTO;
import lombok.Builder;

@Builder
public record GetFullDataEmployeeResponse(EmployeeDTO employeeDTO,
                                          PasportDTO pasportDTO) {

    public static final GetFullDataEmployeeResponse EMPTY = new GetFullDataEmployeeResponse(null, null);
}
