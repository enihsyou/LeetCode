-- Schema
CREATE TABLE IF NOT EXISTS Scores (
  Id    INT,
  Score DECIMAL(3, 2)
);
TRUNCATE TABLE Scores;
INSERT INTO Scores (Id, Score) VALUES ('1', '3.5');
INSERT INTO Scores (Id, Score) VALUES ('2', '3.65');
INSERT INTO Scores (Id, Score) VALUES ('3', '4.0');
INSERT INTO Scores (Id, Score) VALUES ('4', '3.85');
INSERT INTO Scores (Id, Score) VALUES ('5', '4.0');
INSERT INTO Scores (Id, Score) VALUES ('6', '3.65');

-- Solution
SELECT
  Score,
  DENSE_RANK() OVER (
  ORDER BY Score DESC) AS `Rank`
FROM Scores
ORDER BY Score DESC;
