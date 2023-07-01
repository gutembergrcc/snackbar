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
	ticket varchar(50) not null,
	customer_id VARCHAR(50),
	observation VARCHAR(255),
	status varchar(50) not null,
	created_at DATE,
	FOREIGN KEY (customer_id) REFERENCES customers(id),
	primary key (id)
);

CREATE TABLE order_items (
  id varchar(50) not null,
  order_id varchar(50) not null,
  product_id varchar(50) not null,
  quantity INT,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (product_id) REFERENCES products(id),
  primary key (id)
);