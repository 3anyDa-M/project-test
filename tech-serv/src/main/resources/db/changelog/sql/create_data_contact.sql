INSERT INTO contacts (id, contact_type, value, employee_id)
VALUES (1, 'EMAIL', 'ivanov@example.com', 1);

--changeset mikhail:contacts-2
INSERT INTO contacts (id, contact_type, value, employee_id)
VALUES (2, 'PHONE', '+79991234567', 1);

--changeset mikhail:contacts-3
INSERT INTO contacts (id, contact_type, value, employee_id)
VALUES (3, 'TELEGRAM', '@ivanov', 2);