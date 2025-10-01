package com.project.test.tech_serv.repository;

import com.project.test.tech_serv.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface employee_repository extends JpaRepository <Employee , Long> {
}
