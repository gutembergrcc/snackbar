create table customers (
	id varchar(50) not null,
	cpf varchar(11) not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	primary key (id));

alter table customers add constraint UK_customer_cpf unique (cpf);

create table products (
	id varchar(50) not null,
	category varchar(255) not null,
	description varchar(255) not null,
	name varchar(255) not null,
	price decimal(19,2) not null,
	primary key (id));

CREATE TABLE orders (
	id varchar(50) not null,
	order_date DATE,
	customer_id VARCHAR(50),
	FOREIGN KEY (customer_id) REFERENCES customers(id),
	primary key (id)
);

CREATE TABLE order_itens (
  id varchar(50) not null,
  order_id varchar(50) not null,
  product_id varchar(50) not null,
  quantidade INT,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (product_id) REFERENCES products(id),
  primary key (id)
);
