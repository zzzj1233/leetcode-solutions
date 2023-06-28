# 输入:
# Candidate table:
# +----+------+
# | id | name |
# +----+------+
# | 1  | A    |
# | 2  | B    |
# | 3  | C    |
# | 4  | D    |
# | 5  | E    |
# +----+------+
# Vote table:
# +----+-------------+
# | id | candidateId |
# +----+-------------+
# | 1  | 2           |
# | 2  | 4           |
# | 3  | 3           |
# | 4  | 2           |
# | 5  | 5           |
# +----+-------------+
# 输出:
# +------+
# | name |
# +------+
# | B    |
# +------+
# 解释:
# 候选人B有2票。候选人C、D、E各有1票。
# 获胜者是候选人B。

SELECT name
FROM Candidate
where id = (
    SELECT candidateId
    FROM Vote
    group by candidateId
    order by count(candidateId) desc
    limit 1)