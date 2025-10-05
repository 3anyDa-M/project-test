package com.project.test.fileserv.model.Dto.tech_serv;

import lombok.Builder;

@Builder
public record EmployeeDTO(long id,
                          String firstName,
                          String job
                         ) {
}
