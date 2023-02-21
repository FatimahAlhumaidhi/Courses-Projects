drop database if exists estore;
create database estore;
use estore;


create table admin_dat (
	admin_id INT not null AUTO_INCREMENT,
    	username VARCHAR(10) not null,
	password VARCHAR(50) not null,
    	primary key (admin_id)
);

INSERT INTO admin_dat (username, password) VALUES ('admin', 'admin123');

create table customer (
    	customer_id INT not null AUTO_INCREMENT,
    	name VARCHAR(20) not null,
    	email VARCHAR(30) unique not null,
	password VARCHAR(50) not null,
    	primary key (customer_id)
);

create table product (
	id INT not null AUTO_INCREMENT,
    	name VARCHAR(100) not null,
    	photo_path VARCHAR(255),
		photo_url VARCHAR(255),
    	price DECIMAL not null,
	description TEXT,
	quantity INT default 0,
	primary key (id)
);

create table orders (
   	order_number INT not null AUTO_INCREMENT,
   	customer_email VARCHAR(255) not null,
   	order_date  DATE,
   	total_price DECIMAL not null,
    	primary key (order_number),
	foreign key (customer_email) references customer (email) on update cascade
);

-- CREATE TABLE session_data (
--   	session_id varbinary(192) NOT NULL,
--   	created int(11) NOT NULL DEFAULT '0',
--   	session_data longtext COLLATE utf8mb4_unicode_ci,
--   	PRIMARY KEY (session_id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;