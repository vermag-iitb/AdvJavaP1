-- Creating DB
Create Database Demo2Conn;
go 

Use Demo2Conn;
go

--Creating schemas
Create Schema ID_1; 
go
-- Create Schema ID_2;
-- go

Tables
Create table ID_1.Employee
(
    emp_id int Primary Key,
    ename VARCHAR (255) Not NULL,
    salary int check(salary > 1000 and salary < 10000)
);





-- -- Creating DB
-- Create Database Demo2Conn;
-- go 

-- Use Demo2Conn;
-- go

-- --Creating schemas
-- Create Schema ID_1; 
-- go

-- --Tables
-- Create table ID_1.Employee
-- (
--     emp_id int Primary Key,
--     ename VARCHAR (255) Not NULL,
--     salary int check(salary > 1000 and salary < 10000)
-- );

insert into ID_1.Employee values(101, 'aaa', 2000);
insert into ID_1.Employee values(102, 'bbb', 3000);
insert into ID_1.Employee values(103, 'ccc', 4000);
select * from ID_1.Employee;

-- select * from [newworld].[dbo].heartbeat where heartbeat_type='SGE2MX';