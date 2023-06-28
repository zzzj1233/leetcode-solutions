SELECT user_id, max(time_stamp) as 'time_stamp'
FROM Logins
WHERE date_format(time_stamp, '%Y') = 2020
group by user_id