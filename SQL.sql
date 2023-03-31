-- drop table command
DROP TABLE ratereview;
DROP TABLE discount;
DROP TABLE cartlist;
DROP TABLE cart;
DROP TABLE orderlist;
DROP TABLE product;
DROP TABLE orders;
DROP TABLE member_address;
DROP TABLE member;
DROP TABLE addressbook;
DROP TABLE staff;

CREATE TABLE staff(
	staff_id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),
	staff_name varchar(50),
	staff_pass varchar(20),
	staff_ic varchar(12),
	staff_phone varchar(10),
	staff_email varchar(50),
	staff_birthdate date,
	PRIMARY KEY(staff_id)
);

CREATE TABLE addressbook(
	address_id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),
	address_name varchar(255),
	address_phone varchar(10),
	address_no varchar(255),
	address_street varchar(255),
	address_state varchar(255),
	address_city varchar(255),
	address_postcode varchar(255),
	PRIMARY KEY(address_id)
);

CREATE TABLE member(
	member_id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 2000, INCREMENT BY 1),
	member_name varchar(255),
	member_pass varchar(20),
	PRIMARY KEY(member_id)
);

CREATE TABLE member_address(
	address_id integer not null,
	member_id integer not null,
	PRIMARY KEY(address_id, member_id),
	FOREIGN KEY(address_id) REFERENCES addressbook(address_id),
	FOREIGN KEY(member_id) REFERENCES member(member_id)
);


CREATE TABLE orders(
	orders_id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),
	orders_date date,
	orders_payment_type varchar(50), -- CASH PAYMENT OR CARD
	orders_ttlprice double,
	orders_tax double,
	orders_delivery_fee double,
	orders_express_shipping double,
	member_id integer,
	address_id integer,
	PRIMARY KEY(orders_id),
	FOREIGN KEY(member_id) REFERENCES member(member_id),
	FOREIGN KEY(address_id) REFERENCES addressbook(address_id)
);

CREATE TABLE product(
	product_id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),
	product_name varchar(255),
	product_desc varchar(1000),
	product_price double,
	product_active char(1),
	PRIMARY KEY(product_id)
);

CREATE TABLE orderlist(
	orders_id integer not null,
	product_id integer not null,
	orders_quantity integer,
	orders_subprice double,
	PRIMARY KEY(orders_id, product_id),
	FOREIGN KEY(orders_id) REFERENCES orders(orders_id),
	FOREIGN KEY(product_id) REFERENCES product(product_id)
);

CREATE TABLE cart(
	cart_id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	member_id integer,
	PRIMARY KEY(cart_id),
	FOREIGN KEY(member_id) REFERENCES member(member_id)
);

CREATE TABLE cartlist(
	cart_id integer not null,
	product_id integer not null,
	cart_quantity integer,
	PRIMARY KEY(cart_id, product_id),
	FOREIGN KEY(cart_id) REFERENCES cart(cart_id),
	FOREIGN KEY(product_id) REFERENCES product(product_id)
);

CREATE TABLE discount(
	discount_id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),
	discount_percentage integer,
	discount_start_date date,
	discount_end_date date,
	product_id integer,
	PRIMARY KEY(discount_id),
	FOREIGN KEY(product_id) REFERENCES product(product_id)
);

CREATE TABLE ratereview(
	review_id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),
	review_text varchar(255),
	review_rating integer, -- maybe somthing 1 to 5 rating number
	review_date date,
	product_id integer,
	member_id integer,
	PRIMARY KEY(review_id),
	FOREIGN KEY(product_id) REFERENCES product(product_id),
	FOREIGN KEY(member_id) REFERENCES member(member_id)
);

INSERT INTO staff (staff_name, staff_pass, staff_ic, staff_phone, staff_email, staff_birthdate)
VALUES 
('John Doe', 'p@ssw0rd123', '980101101234', '0123456789', 'johndoe@example.com', '1998-01-01'),
('Lin Xiao Ming', 'p@ssw0rd123', '991231105678', '0198765432', 'linxiaoming@example.com', '1999-12-31'),
('Jane Smith', 'p@ssw0rd123', '030202089101', '0176543210', 'janesmith@example.com', '2003-02-02'),
('David Wong', 'p@ssw0rd123', '000712012345', '0167890123', 'davidwong@example.com', '2000-07-12'),
('Zhang Mei Li', 'p@ssw0rd123', '980616103456', '0134567890', 'zhangmeili@example.com', '1998-06-16');

INSERT INTO product (product_name, product_desc, product_price, product_active)
VALUES 
('Lego Creator 3-in-1 Townhouse Toy Store', 'Build a 3-level toy store and townhouse, packed with detailed features and imaginative accessories.', 49.99, '1'),
('Lego Classic Bricks and Animals', 'Discover timeless adventures with this Lego Classic set, featuring colorful bricks and a menagerie of animal models.', 39.99, '0'),
('Lego Harry Potter Hogwarts Great Hall', 'Experience the magic of Hogwarts with this highly detailed Lego Harry Potter set, featuring a grand staircase, potions room, and more.', 99.99, '1'),
('Lego City Space Lunar Space Station', 'Explore the wonders of space with this Lego City set, featuring a modular space station and multiple vehicles and characters.', 69.99, '0'),
('Lego Ninjago Legacy Destiny Bounty', 'Relive classic Ninjago moments with this highly detailed Lego set, featuring a buildable ship and numerous characters and accessories.', 129.99, '1'),
('Lego Friends Heartlake City Airplane', 'Take off on exciting adventures with this Lego Friends airplane set, featuring a detailed cockpit and passenger area.', 59.99, '0'),
('Lego Marvel Avengers Iron Man Hall of Armor', 'Build Tony Stark Hall of Armor with this highly detailed Lego Marvel set, featuring multiple Iron Man suits and accessories.', 59.99, '1'),
('Lego Star Wars Millennium Falcon', 'Join Han Solo and Chewbacca on the iconic Millennium Falcon with this highly detailed Lego Star Wars set, featuring numerous characters and accessories.', 159.99, '0');
