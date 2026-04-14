-- 코드를 입력하세요
SELECT member_id, member_name, gender, DATE_FORMAT(date_of_birth, '%Y-%m%-%d') as DATE_FORMAT
FROM MEMBER_PROFILE
WHERE month(date_of_birth) = '03' and TLNO is not null and gender = 'W'
order by member_id