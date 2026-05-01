SELECT o.animal_id ANIMAL_ID, o.name NAME
FROM ANIMAL_OUTS o
LEFT OUTER JOIN ANIMAL_INS i
ON o.animal_id = i.animal_id
WHERE i.animal_id is null
ORDER BY o.animal_id, o.name