CREATE TABLE IF NOT EXISTS employee (
                                        id BIGINT PRIMARY KEY,
                                        first_name VARCHAR(255) NOT NULL,
                                        job_title VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS passport (
                                        id BIGINT PRIMARY KEY,
                                        employee_id BIGINT,
                                        passport_number VARCHAR(100),
                                        issued_date DATE,
                                        CONSTRAINT fk_passport_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);
CREATE TABLE IF NOT EXISTS contact (
                                       id BIGINT PRIMARY KEY,
                                       employee_id BIGINT,
                                       phone VARCHAR(50),
                                       email VARCHAR(255),
                                       CONSTRAINT fk_contact_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);
