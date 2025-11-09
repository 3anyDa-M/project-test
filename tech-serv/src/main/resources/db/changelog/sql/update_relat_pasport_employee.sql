UPDATE pasport p
SET employee_id = e.id
FROM employee e
WHERE p.number = e.pasport_num;
