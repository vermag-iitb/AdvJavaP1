-- CREATE PROCEDURE  InsertIntoStudent @x INT, @y VARCHAR(255), @z INT
-- AS
-- insert into ID_1.Student values(@x,@y,@z);

-- Once a procedure is defined: 
-- 1. we can only use it and hence above statements are commented
-- 2. procedure got stored inside: Demo2Conn (Schema) -> Programmability -> Stored Procedures -> dbo.InsertIntoStudent

-- EXECUTE InsertIntoStudent 108, 'hhh', 91;
-- select * from ID_1.Student;

-- Query to add a column of image type to the table: 
-- *************************************************
-- ALTER table ID_1.Student ADD Photo varbinary(MAX);
-- ALTER table ID_1.Student ALTER COLUMN Photo IMAGE;

-- Query to select/update a record with image:
-- *************************************************
-- SELECT * FROM OPENROWSET(
--    BULK '/var/opt/mssql/data/images/bird.jpeg',
--    SINGLE_BLOB) AS DATA;

-- UPDATE ID_1.Student
-- set Photo = 
-- (
--     SELECT * FROM 
--     OPENROWSET(BULK '/var/opt/mssql/data/images/bird.jpeg', SINGLE_BLOB)
--     as img1
-- )
-- where std_id=101;

-- Declare @sql nvarchar(max);

-- SET @sql="UPDATE [Demo2Conn].ID_1.Student 
-- set Photo =
-- (
--     SELECT * FROM
--     OPENROWSET(BULK '/var/opt/mssql/data/images/bird.jpeg', SINGLE_BLOB)
--     as img5
-- )
-- where std_id=105;"

-- exec(@sql);

-- Query to update a record:
-- ************************
-- Update ID_1.Student
-- set Maths=79
-- where std_id=101;

-- select * from ID_1.Student
-- where std_id=101;