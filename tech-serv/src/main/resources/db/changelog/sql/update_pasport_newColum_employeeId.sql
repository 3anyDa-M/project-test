ALTER TABLE pasport
ADD COLUMN employee_id BIGINT;

ALTER TABLE pasport
ADD CONSTRAINT fk_pasport_employee
FOREIGN KEY (employee_id) REFERENCES employee(id);
