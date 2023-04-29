CREATE TABLE customer (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	first_name varchar(100) not null,
	last_name varchar(100) not null
);

CREATE TABLE mobile_numbers (
	MOBILE_NUMBER varchar(15) not null unique,
	cust_id BIGINT not null
);

ALTER TABLE mobile_numbers ADD CONSTRAINT MOBILE_NUM_MAPPING FOREIGN KEY (cust_id) REFERENCES customer(id);
