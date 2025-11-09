package com.project.test.fileserv.model.Dto.tech_serv.contract;

import com.project.test.fileserv.model.Dto.tech_serv.EmployeeDTO;
import com.project.test.fileserv.model.Dto.tech_serv.PasportDTO;
import lombok.Builder;

@Builder
public record GetFullDataEmployeeResponse(EmployeeDTO employeeDTO,
                                          PasportDTO pasportDTO) {

    public static final GetFullDataEmployeeResponse EMPTY = new GetFullDataEmployeeResponse(null, null);
}
