package com.project.test.tech_serv.config.dataInitializer;

import com.project.test.tech_serv.model.Contact;
import com.project.test.tech_serv.model.Employee;
import com.project.test.tech_serv.model.Pasport;
import com.project.test.tech_serv.model.enums.ContatcType;
import com.project.test.tech_serv.repository.ContactRepository;
import com.project.test.tech_serv.repository.EmployeeRepository;
import com.project.test.tech_serv.repository.PasportRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final EmployeeRepository employeeRepository;
    private final ContactRepository contactRepository;
    private final PasportRepository pasportRepository;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    private static final int EMPLOYEE_COUNT = 10000; // 👉 количество записей

    @PostConstruct
    @Transactional
    public void init() {
        String initFlag = System.getenv("INIT_FAKE_DATA");
        if (initFlag == null || !initFlag.equalsIgnoreCase("true")) {
            System.out.println("⚠️ Генерация отключена — установи INIT_FAKE_DATA=true, чтобы включить.");
            return;
        }
        if (employeeRepository.count() > 0) {
            System.out.println("⚠️ Данные уже существуют — генерация пропущена.");
            return;
        }

        List<Employee> employees = new ArrayList<>();
        List<Contact> contacts = new ArrayList<>();
        List<Pasport> pasports = new ArrayList<>();

        for (int i = 0; i < EMPLOYEE_COUNT; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String passportNum = faker.number().digits(10);

            Employee employee = Employee.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .job(faker.job().title())
                    .pasportNum(passportNum)
                    .build();
            employees.add(employee);

            Pasport pasport = Pasport.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .pasportNum(passportNum)
                    .address(faker.address().fullAddress())
                    .employee(employee)
                    .build();
            pasports.add(pasport);

            int contactsCount = 1 + random.nextInt(3);
            for (int j = 0; j < contactsCount; j++) {
                Contact contact = Contact.builder()
                        .contatcType(random.nextBoolean() ? ContatcType.EMAIL : ContatcType.PHONE)
                        .value(random.nextBoolean()
                                ? faker.internet().emailAddress()
                                : faker.phoneNumber().cellPhone())
                        .employee(employee)
                        .build();
                contacts.add(contact);
            }
        }

        employeeRepository.saveAll(employees);
        pasportRepository.saveAll(pasports);
        contactRepository.saveAll(contacts);

        System.out.println("✅ Сгенерировано " + employees.size() + " сотрудников, " +
                           contacts.size() + " контактов и " + pasports.size() + " паспортов.");
    }
}
