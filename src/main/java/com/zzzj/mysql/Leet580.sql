# 输入:
# Student 表:
# +------------+--------------+--------+---------+
# | student_id | student_name | gender | dept_id |
# +------------+--------------+--------+---------+
# | 1          | Jack         | M      | 1       |
# | 2          | Jane         | F      | 1       |
# | 3          | Mark         | M      | 2       |
# +------------+--------------+--------+---------+
# Department 表:
# +---------+-------------+
# | dept_id | dept_name   |
# +---------+-------------+
# | 1       | Engineering |
# | 2       | Science     |
# | 3       | Law         |
# +---------+-------------+
# 输出:
# +-------------+----------------+
# | dept_name   | student_number |
# +-------------+----------------+
# | Engineering | 2              |
# | Science     | 1              |
# | Law         | 0              |
# +-------------+----------------+

SELECT dept_name, if(a2.ct is null, 0, a2.ct) as student_number
FROM Department as a1
         LEFT OUTER JOIN (SELECT count(dept_id) as ct, dept_id FROM Student group by dept_id) as a2
                         on a1.dept_id = a2.dept_id
order by student_number desc, dept_name asc