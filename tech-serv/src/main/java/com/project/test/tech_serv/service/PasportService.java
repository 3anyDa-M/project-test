package com.project.test.tech_serv.service;

import com.project.test.tech_serv.DTO.entity.PasportDTO;

import java.util.List;

public interface PasportService {
    List <PasportDTO> findByFirstName(String firstName);
}
