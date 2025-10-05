package com.project.test.tech_serv.service;

import com.project.test.tech_serv.DTO.entity.PasportDTO;

public interface PasportService {
    PasportDTO findByFirstName(String firstName);
}
