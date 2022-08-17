# Employees 表:
# +-------------+---------+--------+
# | employee_id | name    | salary |
# +-------------+---------+--------+
# | 2           | Meir    | 3000   |
# | 3           | Michael | 3800   |
# | 7           | Addilyn | 7400   |
# | 8           | Juan    | 6100   |
# | 9           | Kannon  | 7700   |
# +-------------+---------+--------+
# 输出：
# +-------------+-------+
# | employee_id | bonus |
# +-------------+-------+
# | 2           | 0     |
# | 3           | 0     |
# | 7           | 7400  |
# | 8           | 0     |
# | 9           | 7700  |

SELECT employee_id,
       (case
            when employee_id % 2 = 0 then 0
            when employee_id % 2 = 1 and substring(name, 1, 1) = 'M' then 0
            ELSE salary end) as bonus
FROM Employees
ORDER BY employee_id