package com.project.test.tech_serv.DTO.entity;

import lombok.Builder;

@Builder
public record PasportDTO(String firstName,
                         String lastName,
                         String pasportNum,
                         String address) {}
