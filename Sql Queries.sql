create database march_java;
use march_java;
create table employeedetail
(
empid int primary key auto_increment ,
emp_name varchar(255),
branch varchar(255), 
department varchar(255),
salary double
);
INSERT INTO EMPLOYEEDETAIL (empid, emp_name, branch, department, salary) VALUES
(1, 'Amit Sharma', 'Mumbai', 'HR', 75000.00),
(2, 'Priya Patel', 'Delhi', 'IT', 82000.50),
(3, 'Rajesh Kumar', 'Bangalore', 'Finance', 90000.75),
(4, 'Neha Verma', 'Mumbai', 'HR', 78000.25),
(5, 'Vikram Singh', 'Delhi', 'IT', 85000.00),
(6, 'Anjali Gupta', 'Bangalore', 'Finance', 92000.40),
(7, 'Manish Tiwari', 'Mumbai', 'HR', 76000.90),
(8, 'Kavita Reddy', 'Chennai', 'IT', 89000.30),
(9, 'Arjun Nair', 'Bangalore', 'Finance', 94000.20),
(10, 'Sneha Iyer', 'Chennai', 'HR', 77000.10),
(11, 'Suresh Pillai', 'Mumbai', 'IT', 81000.60),
(12, 'Divya Menon', 'Delhi', 'Finance', 93000.80),
(13, 'Ravi Shankar', 'Chennai', 'HR', 74000.55),
(14, 'Pooja Deshmukh', 'Bangalore', 'IT', 86000.95),
(15, 'Karan Mehta', 'Mumbai', 'Finance', 97000.70),
(16, 'Meera Joshi', 'Delhi', 'HR', 73000.15),
(17, 'Akash Bansal', 'Chennai', 'IT', 88000.45),
(18, 'Rohan Agarwal', 'Delhi', 'Finance', 95000.60),
(19, 'Swati Saxena', 'Bangalore', 'HR', 72000.85),
(20, 'Vivek Choudhary', 'Chennai', 'Finance', 96000.90);

-- SQL : Java(variable,loops,conditional statement,functions) --pl/sql

-- updates the salary on the basis of some conditions
-- Procedure is like the functions that are created in other programming languages

Delimiter $$
create procedure emp_salary_incre()
BEGIN
	-- declaring variables
	declare ename varchar(255);
    declare esalary double default 0;
    declare fin_salary double;
    -- Assigning Values
    set ename = 'Dhinesh';
    set esalary = 150000;
    -- Additional Calculations - 8%
    set fin_salary = esalary + (esalary * 0.08);
    
    -- Displaying the output
    select concat('Employee Name: ' , ename, ' Final Salary: ', fin_salary) as Final_output;
END
$$
call emp_salary_incre();

delimiter $$
/* Creating the stored procedure to update the salary of employee with id and increment parameter and update it in the table */
create procedure update_emp_salary(IN param_id int, IN param_increment double)
BEGIN
	DECLARE variable_sal double;
    DECLARE final_salary double;
    
    -- fetching current salary
    select salary into variable_sal from employeedetail where empid = param_id;
    SET final_salary = variable_sal + (variable_sal*(param_increment/100));
    
    update employeedetail set salary = final_salary where empid = param_id;
    select concat('Salary of Employee with id : ' , param_id,'has been updated') as Output;	
END;
$$
call update_emp_salary(1,20);
select * from employeedetail;

-- LOOPING (FOR)
delimiter $$
create procedure display()
BEGIN
	declare i int default 0;
    declare result text default '';
    
    my_loop : LOOP
		IF i>5 THEN
			LEAVE my_loop;
		END IF;
        SET result = concat(result,(i+1) , '  ');
        set i = i+1;
	END LOOP my_loop;
     select result;
END;
$$
drop procedure display;
call display();

-- LOOPING (WHILE)
delimiter $$
create procedure display_with_while()
BEGIN
	declare i int default 0;
    declare result text default '';
    
    while i<5 do
		set result = concat(result, (i+1),'  ');
        set i = i+1;
	END while;
     select result;
END;
$$
drop procedure display_with_while;
call display_with_while();


-- update the salary with respect department with respect % of increment
/** 
Department          increment%
---------			------------
  HR 					5%
  IT 					7%
  FINANCE 				10%
**/
delimiter $$
create procedure update_sal_dept(IN param_dept varchar(255), IN param_increment double)
BEGIN
	declare cnt int;
    declare i int default 0;
    declare eid int;
    declare edept varchar(255);
    select count(*) into cnt from employeedetail;
    while i<cnt do
		select empid, department into eid,edept from employeedetail LIMIT i,1;
        IF edept = param_dept then
			update employeedetail set salary = salary + (salary*(param_increment/100)) where empid = eid;
		END IF;
		SET i=i+1;
        end while;
END;
$$
drop procedure update_sal_dept;
call update_sal_dept("IT",7);

delimiter $$
create procedure displayRecords(IN param_tbl_name varchar(255), IN param_orderby varchar(255), IN param_limit int)
BEGIN
	SET @query = concat(' select * from ' , param_tbl_name , ' order by ' , param_orderby , ' limit ' ,param_limit);
    prepare stmt from @query;
    execute stmt;
    deallocate prepare stmt;
END;
$$
drop procedure displayRecords;
call displayRecords("employeedetail","department",4);

select * from employeedetail;

-- call(tblename,column name, column value)
delimiter $$
create procedure tblecolnameandvalue(IN Tb_name varchar(255),In colum_name varchar(255),In colum_value varchar(255))
BEGIN 
set @query=concat('select * from ',Tb_name,' where ',colum_name,' = "',colum_value,'"');
prepare stmt from @query;
execute stmt;
deallocate prepare stmt;
END;
$$
 
 drop procedure tblecolnameandvalue;
call tblecolnameandvalue("employeedetail","branch",'mumbai');    

-- OUT PARAMETER
 delimiter $$
 create procedure outProc(OUT cnt INT)
 BEGIN
	select count(empid) into cnt from employeedetail;
 END;
 $$
 
 -- session variable for storing the output came from the above procedure
 SET @num_employee =0;
 call outProc(@num_employee);
 select @num_employee;
 
 -- INOUT PARAMETER --
 DELIMITER $$
 create procedure in_out_Proc(INOUT num INT)
 BEGIN
	select salary into num from employeedetail where empid = num;
 END;
 $$
 
 drop procedure in_out_Proc;
 -- session variable for storing the output came from the above procedure
 SET @num = 8; -- This is a session variable it may be any of the name
 call in_out_Proc(@num);
 select @num;
 
 
-- TRIGGERS
create table employee_log (id int primary key auto_increment, old_salary double, new_salary double, date_of_update date, username varchar(255));

delimiter $$
create trigger emp_salary_update_trigger
BEFORE update on Employeedetail
for each row
BEGIN
	insert into employee_log(old_salary,new_salary,date_of_update,username)
    values(OLD.salary,NEW.salary,now(),user()); -- This username can be changed by the current user function otherrise can we give it manually
END;
$$

drop trigger emp_salary_update_trigger;
UPDATE employeedetail set salary = 150000 where empid = 3; -- for calling the trigger
select user();
select current_user()
select session_user()

-- VIEWS : Triming the whole table into necessary details to be shown

create or replace view employee_detail AS
select empid,emp_name,branch,department
from employeedetail -- this is enough for horizontal trimming (ex: column triming)
where department <> "Finance"; -- this is to trim Vertically(ex: row trim)

select * from employee_detail;

-- FUNCTIONS : It return the value 
/*A routine is considered “deterministic” if it always produces the same result for the
 same input parameters, and “not deterministic” otherwise.
 If neither DETERMINISTIC nor NOT DETERMINISTIC is given in the routine definition, 
 the default is NOT DETERMINISTIC. 
To declare that a function is deterministic, you must specify DETERMINISTIC explicitly.*/

delimiter $$
create function emp_func(param_id int) RETURNS double
 DETERMINISTIC
 BEGIN
	declare esal double;
    
    select salary into esal
    from employeedetail
    where empid = param_id;

	return esal;
 END;
 -- Calling the deterministic function
 select emp_func(3) AS "Salary value";
drop function emp_func;
 
 /* NON DETERMINISTIC FUNCTIONS EXAMPLE BUT HERE BY DEFAULT IT WILL TAKE AS NOT DETERMINISTIC
 WE NEED NOT TO DECLARE IT NOT DETERMINISTIC*/
 
DELIMITER $$ 
create function current_date_func() returns date     -- by default, NOT DETERMINISTIC
 BEGIN
	declare curr date; 
    select NOW() into curr; 
	return curr;
END;
$$ 
drop function current_date_func;
/*
select NOW()  -- These two are the Not Determisitic type of functions
RAND() 
*/
-- calling the func
select current_date_func() AS "CURRENT_DATE";

-- ER DIAGRAM
/*
product		M:1		category 
   category_id
 
 
customer		category    		
 
 
PO(Product Owner)     ----> Client
1. Customer buying product
 
-- is customer allowed to buy multiple products YES(1:M)   NO(1:1)
 
product bought by customer
 
-- can a product be bought by multiple customers
Apple iPhone SE (10)
 
NO(1:1)      YES(1:M)
 
 
customer ---1:M----  product   
customer----M:1----  product	
	    M:M 	
----------------------------
	customer_product 
	customer_id,product_id
 
 
Vendor sells the product.
 
---> can a vendor select multiple products?????
	vendor(1) --- (M)product
 
---> can a product be sold by many vendors 
	product(1) --- (1)vendor 
	oppo CE3 (50)
 
vendor - product
1:M
product - vendor 
1:1
1:M
product  vendor_id
To create a foreign key, take the PK of relation that is towards 1 and add it to other table as foreign key
review  product,customer
review  	1:1	product	
product		1:M	review   : review(product_id)
 
review  	1:1	customer	
customer	1:M	review  : review(cstomer_id)
*/

