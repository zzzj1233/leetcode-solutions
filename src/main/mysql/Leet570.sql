# 输入:
# Employee 表:
# +-----+-------+------------+-----------+
# | id  | name  | department | managerId |
# +-----+-------+------------+-----------+
# | 101 | John  | A          | None      |
# | 102 | Dan   | A          | 101       |
# | 103 | James | A          | 101       |
# | 104 | Amy   | A          | 101       |
# | 105 | Anne  | A          | 101       |
# | 106 | Ron   | B          | 101       |
# +-----+-------+------------+-----------+
# 输出:
# +------+
# | name |
# +------+
# | John |
# +------+

SELECT a1.name
from Employee as a1
         inner join (SELECT managerId FROM Employee GROUP BY managerId having count(managerId) >= 5) as a2
                    on a1.id = a2.managerId
