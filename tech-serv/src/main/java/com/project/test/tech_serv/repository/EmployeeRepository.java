package com.project.test.tech_serv.repository;

import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import com.project.test.tech_serv.DTO.entity.EmployeeDTO;
import com.project.test.tech_serv.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee , Long> {

    @Query("""
       SELECT new com.project.test.tech_serv.DTO.entity.EmployeeDTO(e.id, e.firstName, e.job) 
       FROM Employee e 
       WHERE e.firstName = :firstName
       """)
    List<EmployeeDTO> findByFirstName(@Param("firstName") String firstName);

    Employee findById(long id);


    @Query(value = """
    SELECT emp.id,emp.first_name , emp.job, 
           psp.last_name , psp.number , psp.address
    
    FROM employee emp
    JOIN pasport psp ON emp.id = psp.employee_id
    WHERE emp.first_name = :firstName
""", nativeQuery = true)
    GetFullDataEmployeeResponse getFullfindByFirstName(@Param("firstName") String firstName);

}
