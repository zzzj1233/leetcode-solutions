# 输入：
# Departments 表:
# +------+--------------------------+
# | id   | name                     |
# +------+--------------------------+
# | 1    | Electrical Engineering   |
# | 7    | Computer Engineering     |
# | 13   | Bussiness Administration |
# +------+--------------------------+
# Students 表:
# +------+----------+---------------+
# | id   | name     | department_id |
# +------+----------+---------------+
# | 23   | Alice    | 1             |
# | 1    | Bob      | 7             |
# | 5    | Jennifer | 13            |
# | 2    | John     | 14            |
# | 4    | Jasmine  | 77            |
# | 3    | Steve    | 74            |
# | 6    | Luis     | 1             |
# | 8    | Jonathan | 7             |
# | 7    | Daiana   | 33            |
# | 11   | Madelynn | 1             |
# +------+----------+---------------+
# 输出：
# +------+----------+
# | id   | name     |
# +------+----------+
# | 2    | John     |
# | 7    | Daiana   |
# | 4    | Jasmine  |
# | 3    | Steve    |
# +------+----------+
# 解释：
# John, Daiana, Steve 和 Jasmine 所在的院系分别是 14, 33, 74 和 77， 其中 14, 33, 74 和 77 并不存在于院系表

SELECT s.id as id, s.name as name
FROM Students as S
         INNER JOIN Departments as D
                          on S.department_id = D.id
WHERE D.name is null