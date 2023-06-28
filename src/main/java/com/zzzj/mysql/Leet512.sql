# Activity table:
# +-----------+-----------+------------+--------------+
# | player_id | device_id | event_date | games_played |
# +-----------+-----------+------------+--------------+
# | 1         | 2         | 2016-03-01 | 5            |
# | 1         | 2         | 2016-05-02 | 6            |
# | 2         | 3         | 2017-06-25 | 1            |
# | 3         | 1         | 2016-03-02 | 0            |
# | 3         | 4         | 2018-07-03 | 5            |
# +-----------+-----------+------------+--------------+
#
# Result table:
# +-----------+-----------+
# | player_id | device_id |
# +-----------+-----------+
# | 1         | 2         |
# | 2         | 3         |
# | 3         | 1         |
# +-----------+-----------+


SELECT a1.player_id, a1.device_id
FROM Activity as a1
         INNER join (SELECT min(event_date) as event_date, player_id FROM Activity group by player_id) as a2
                    on a1.player_id = a2.player_id and a1.event_date = a2.event_date
