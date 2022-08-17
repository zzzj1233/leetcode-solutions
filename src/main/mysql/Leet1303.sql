# Employee Table:
# +-------------+------------+
# | employee_id | team_id    |
# +-------------+------------+
# |     1       |     8      |
# |     2       |     8      |
# |     3       |     8      |
# |     4       |     7      |
# |     5       |     9      |
# |     6       |     9      |
# +-------------+------------+
# Result table:
# +-------------+------------+
# | employee_id | team_size  |
# +-------------+------------+
# |     1       |     3      |
# |     2       |     3      |
# |     3       |     3      |
# |     4       |     1      |
# |     5       |     2      |
# |     6       |     2      |
# +-------------+------------+
# ID 为 1、2、3 的员工是 team_id 为 8 的团队的成员，
# ID 为 4 的员工是 team_id 为 7 的团队的成员，
# ID 为 5、6 的员工是 team_id 为 9 的团队的成员。

SELECT employee_id, t2.team_size as team_size
FROM Employee AS e
         inner join
         (SELECT count(1) AS team_size, team_id FROM Employee GROUP BY team_id) as t2
         on e.team_id = t2.team_id