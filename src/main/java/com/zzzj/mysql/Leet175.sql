# 输入:
# Person表:
# +----------+----------+-----------+
# | personId | lastName | firstName |
# +----------+----------+-----------+
# | 1        | Wang     | Allen     |
# | 2        | Alice    | Bob       |
# +----------+----------+-----------+
# Address表:
# +-----------+----------+---------------+------------+
# | addressId | personId | city          | state      |
# +-----------+----------+---------------+------------+
# | 1         | 2        | New York City | New York   |
# | 2         | 3        | Leetcode      | California |
# +-----------+----------+---------------+------------+
# 输出:
# +-----------+----------+---------------+----------+
# | firstName | lastName | city          | state    |
# +-----------+----------+---------------+----------+
# | Allen     | Wang     | Null          | Null     |
# | Bob       | Alice    | New York City | New York |
# +-----------+----------+---------------+----------+
# 解释:
# 地址表中没有 personId = 1 的地址，所以它们的城市和州返回 null。
# addressId = 1 包含了 personId = 2 的地址信息。

SELECT firstName, lastName, city, state
FROM Person as p
         LEFT OUTER JOIN Address as a
                         on p.personId = a.personId