# 输入：
# SurveyLog table:
# +----+--------+-------------+-----------+-------+-----------+
# | id | action | question_id | answer_id | q_num | timestamp |
# +----+--------+-------------+-----------+-------+-----------+
# | 5  | show   | 285         | null      | 1     | 123       |
# | 5  | answer | 285         | 124124    | 1     | 124       |
# | 5  | show   | 369         | null      | 2     | 125       |
# | 5  | skip   | 369         | null      | 2     | 126       |
# +----+--------+-------------+-----------+-------+-----------+
# 输出：
# +------------+
# | survey_log |
# +------------+
# | 285        |
# +------------+
# 解释：
# 问题 285 显示 1 次、回答 1 次。回答率为 1.0 。
# 问题 369 显示 1 次、回答 0 次。回答率为 0.0 。
# 问题 285 回答率最高。


SELECT question_id as survey_log
FROM SurveyLog
group by question_id
order by sum(if(action = 'answer', 1, 0)) / sum(if(action = 'show', 1, 0)) desc, question_id asc
limit 1;
