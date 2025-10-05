package com.project.test.tech_serv.DTO.entity;

import lombok.Builder;

@Builder
public record EmployeeDTO(long id,
                          String firstName,
                          String job
                         ) {
}
