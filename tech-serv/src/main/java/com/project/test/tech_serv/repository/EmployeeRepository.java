package com.project.test.tech_serv.repository;

import com.project.test.tech_serv.DTO.entity.EmployeeDTO;
import com.project.test.tech_serv.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
@Repository
public interface EmployeeRepository extends JpaRepository <Employee , Long> {
    EmployeeDTO findByFirstName(String firstName);
}
