package com.project.test.tech_serv.service.impl;

import com.project.test.tech_serv.DTO.entity.PasportDTO;
import com.project.test.tech_serv.repository.PasportRepository;
import com.project.test.tech_serv.service.PasportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PasportServiceImpl implements PasportService {
    private final PasportRepository pasportRepository;

    @Override
    public List<PasportDTO> findByFirstName(String firstName) {
        return pasportRepository.findByFirstName(firstName);
    }
}
