CREATE TABLE category (
	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);

CREATE TABLE user_detail (
	id IDENTITY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number VARCHAR(15),	
	CONSTRAINT pk_user_id PRIMARY KEY(id)
);


CREATE TABLE product (
	id IDENTITY,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id),	
);	


CREATE TABLE cart_line (
id IDENTITY,
cart_id int,
total DECIMAL(10,2),
product_id int,
product_count int,
buying_price DECIMAL(10,2),
is_available boolean,
CONSTRAINT fk_cartline_cart_id FOREIGN KEY (cart_id) REFERENCES cart (id),
CONSTRAINT fk_cartline_product_id FOREIGN KEY (product_id) REFERENCES product (id),
CONSTRAINT pk_cartline_id PRIMARY KEY (id)
);

-- adding three categories
INSERT INTO category (name, description,image_url,is_active) VALUES ('Laptop', 'This is description for Laptop category!', 'CAT_1.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Television', 'This is description for Television category!', 'CAT_2.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Mobile', 'This is description for Mobile category!', 'CAT_3.png', true);

-- adding three users 
INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Anshul', 'Gupta', 'ADMIN', true, '$2b$10$lK6eZtsdxx1wYwn28tYqNusCuu1tXJGq3u2QKOw4ibjKGjUgdwEke', 'ag@gmail.com', '6602382090');
INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Abhideep', 'Dharga', 'SUPPLIER', true, '$2b$10$7LZ55jHVU/jh2xPbk8Z.Ee9PjQW3TzvLVUFITFy.OjS1UFJH/S58m', 'ad@gmail.com', '6602381120');
INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Devanjal', 'Kotia', 'USER', true, '$2b$10$JwhKnvKw8CFnhOGGmEh/ru/NeLUWd6iAVrM1Na3mLRkLltLUWaQJW', 'dk@gmail.com', '9425952708');

-- adding five products
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDABC123DEFX', 'iPhone 5s', 'apple', 'This is one of the best phone available in the market right now!', 900, 5, true, 3, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDDEF123DEFX', 'Samsung s7', 'samsung', 'A smart phone by samsung!', 700, 2, true, 3, 3, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDPQR123WGTX', 'Google Pixel', 'google', 'This is one of the best android smart phone available in the market right now!', 800, 5, true, 3, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDMNO123PQRX', ' Macbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 1300, 3, true, 1, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDABCXYZDEFX', 'Dell Latitude E6510', 'dell', 'This is one of the best laptop series from dell that can be used!', 1000, 5, true, 1, 3, 0, 0 );