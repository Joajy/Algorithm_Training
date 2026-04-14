
-- 사원별 성과금 정보
-- 기준점수, 평가등급, 성과급(연봉기준)
SELECT B.EMP_NO, B.EMP_NAME,
       CASE WHEN SUM(C.SCORE) / COUNT(*) >= 96 THEN 'S'
            WHEN SUM(C.SCORE) / COUNT(*) >= 90 THEN 'A'
            WHEN SUM(C.SCORE) / COUNT(*) >= 80 THEN 'B'
            ELSE 'C'
        END AS GRADE,
       CASE WHEN SUM(C.SCORE) / COUNT(*) >= 96 THEN 0.2 * B.SAL
            WHEN SUM(C.SCORE) / COUNT(*) >= 90 THEN 0.15* B.SAL
            WHEN SUM(C.SCORE) / COUNT(*) >= 80 THEN 0.1 * B.SAL
            ELSE 0
        END AS BONUS
  FROM HR_DEPARTMENT A
     , HR_EMPLOYEES  B
     , HR_GRADE      C
 WHERE A.DEPT_ID = B.DEPT_ID
   AND B.EMP_NO  = C.EMP_NO
 GROUP BY B.EMP_NO, B.EMP_NAME
 ORDER BY B.EMP_NO;