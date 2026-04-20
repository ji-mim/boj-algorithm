-- 코드를 작성해주세요
SELECT sum(g.score) as SCORE, e.emp_no as EMP_NO, e.emp_name as EMP_NAME, e.POSITION as POSITION, e.email AS EMAIL
FROM HR_DEPARTMENT as d 
JOIN HR_EMPLOYEES as e ON d.dept_id = e.dept_id 
JOIN HR_GRADE as g ON e.emp_no = g.emp_no
GROUP BY e.emp_no, e.emp_name, e.position, e.email
ORDER BY SCORE DESC
LIMIT 1;
                   