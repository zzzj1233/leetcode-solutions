# Stocks 表:
# +---------------+-----------+---------------+--------+
# | stock_name    | operation | operation_day | price  |
# +---------------+-----------+---------------+--------+
# | Leetcode      | Buy       | 1             | 1000   |
# | Corona Masks  | Buy       | 2             | 10     |
# | Leetcode      | Sell      | 5             | 9000   |
# | Handbags      | Buy       | 17            | 30000  |
# | Corona Masks  | Sell      | 3             | 1010   |
# | Corona Masks  | Buy       | 4             | 1000   |
# | Corona Masks  | Sell      | 5             | 500    |
# | Corona Masks  | Buy       | 6             | 1000   |
# | Handbags      | Sell      | 29            | 7000   |
# | Corona Masks  | Sell      | 10            | 10000  |
# +---------------+-----------+---------------+--------+
#
# Result 表:
# +---------------+-------------------+
# | stock_name    | capital_gain_loss |
# +---------------+-------------------+
# | Corona Masks  | 9500              |
# | Leetcode      | 8000              |
# | Handbags      | -23000            |
# +---------------+-------------------+
# Leetcode 股票在第一天以1000美元的价格买入，在第五天以9000美元的价格卖出。资本收益=9000-1000=8000美元。
# Handbags 股票在第17天以30000美元的价格买入，在第29天以7000美元的价格卖出。资本损失=7000-30000=-23000美元。
# Corona Masks 股票在第1天以10美元的价格买入，在第3天以1010美元的价格卖出。在第4天以1000美元的价格再次购买，在第5天以500美元的价格出售。最后，它在第6天以1000美元的价格被买走，在第10天以10000美元的价格被卖掉。资本损益是每次（’Buy'->'Sell'）操作资本收益或损失的和=（1010-10）+（500-1000）+（10000-1000）=1000-500+9000=9500美元。

SELECT stock_name, sum(if(operation = 'Sell', price, -price)) as capital_gain_loss
FROM Stocks
group by stock_name