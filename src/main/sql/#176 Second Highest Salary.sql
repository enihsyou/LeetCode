-- Schema
CREATE TABLE IF NOT EXISTS Employee (
  Id     INT,
  Salary INT
);
TRUNCATE TABLE Employee;
INSERT INTO Employee (Id, Salary)
VALUES ('1', '100');
INSERT INTO Employee (Id, Salary)
VALUES ('2', '200');
INSERT INTO Employee (Id, Salary)
VALUES ('3', '300');

-- Solution
SELECT (
         SELECT DISTINCT Salary
         FROM Employee
         ORDER BY Salary DESC LIMIT 1 OFFSET 1) AS SecondHighestSalary;

-- Solution2
-- #177. Nth Highest Salary
SET GLOBAL log_bin_trust_function_creators = 1;
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT BEGIN
  DECLARE M INT;
  SET M = N -1;
  RETURN (
    SELECT DISTINCT Salary
    FROM Employee
    ORDER BY Salary DESC LIMIT 1 OFFSET M);
END;
SELECT getNthHighestSalary(1)
