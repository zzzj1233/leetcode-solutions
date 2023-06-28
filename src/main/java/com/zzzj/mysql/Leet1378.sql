SELECT unique_id, name
FROM Employees as e1
         left outer join EmployeeUNI as e2
                         on e1.id = e2.id