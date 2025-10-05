package com.project.test.fileserv.model.Dto;

import com.project.test.fileserv.model.enums.DocumentType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record DocumentDto(UUID id,
                          DocumentType documentType,
                          String name,
                          LocalDateTime creationDatetime,
                          LocalDateTime expirationDatetime){

}
