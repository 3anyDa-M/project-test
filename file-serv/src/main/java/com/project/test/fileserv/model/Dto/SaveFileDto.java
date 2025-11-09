package com.project.test.fileserv.model.Dto;

import com.project.test.fileserv.model.Dto.tech_serv.contract.GetFullDataEmployeeResponse;
import lombok.Builder;

@Builder
public record SaveFileDto(DocumentDto documentDto
        , GetFullDataEmployeeResponse getFullDataEmployeeResponse) {
}
