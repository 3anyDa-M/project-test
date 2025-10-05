package com.project.test.fileserv.model.Dto.tech_serv;

import lombok.Builder;

@Builder
public record PasportDTO(String firstName,
                         String lastName,
                         String pasportNum,
                         String address) {}
