create database e_com_db;

use e_com_db;

create table customer(cus_id int primary key auto_increment,cus_name varchar(255),contact varchar(255));

create table address(add_id int primary key ,city varchar(255),pincode varchar(255));

create table product(prod_id int primary key auto_increment ,prod_title varchar(255),price double,quantity int);
drop table product;
insert into product(prod_title,price,quantity,cate_id) values("Mobile",25000,40,1);
select * from product;

create table category(cat_id int primary key auto_increment,cat_name varchar(255),priority int);

desc product;

alter table product add column (cate_id int not null);

insert into category(cat_name, priority) values 
("Electronics", 10),
("Clothing", 4),
("Books", 8),
("Furniture", 2),
("Toys", 6),
("Groceries", 1),
("Health & Beauty", 9),
("Sports", 5),
("Automotive", 7),
("Home Appliances", 3);

drop table category;