-- 코드를 입력하세요
SELECT ai.animal_id as ANIMAL_ID, ai.name as NAME
FROM ANIMAL_INS as ai 
JOIN ANIMAL_OUTS as ao 
ON ai.animal_id = ao.animal_id
WHERE ai.datetime > ao.datetime
ORDER BY ai.datetime ASC
