package com.project.test.tech_serv.repository;

import com.project.test.tech_serv.DTO.entity.PasportDTO;
import com.project.test.tech_serv.model.Pasport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasportRepository extends JpaRepository<Pasport,String> {
   List<PasportDTO> findByFirstName(String firstName);
}
