package com.project.test.tech_serv.controller.outer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import com.project.test.tech_serv.TechServApplicationTests;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;


@RequiredArgsConstructor
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,value = "/scripts/controller/employeeControllerSchema.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,value = "/scripts/clear.sql")
public class EmployeeControllerTest extends TechServApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertThat(POSTGRES.isRunning()).isTrue();
    }

    @Test
    void shouldReturnEmployeesList() {
        String url = "http://localhost:" + port + "/api/employee";
        String response = restTemplate.getForObject(url, String.class);
        assertThat(response).contains("employee");
    }

    @Test
    void shouldReturnFullDataEmployeeResponse() throws Exception {
        String firstName = "Mckinley Hand";
        boolean kafkaSend = false;

        String url = "http://localhost:" + port + "/api/employee?firstName=" + firstName + "&kafkaSend=" + kafkaSend;

        GetFullDataEmployeeResponse response =
                restTemplate.getForObject(url, GetFullDataEmployeeResponse.class);

                assertThat(response).isNotNull();
                assertThat(response.employeeDTO()).isNotEmpty();

                var employee  =  response.employeeDTO().get(0);
                assertThat(employee.firstName()).isEqualTo(firstName);

    }

}