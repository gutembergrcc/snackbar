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


create table orders (
    id varchar(50) not null,
    product_id varchar(50) not null,
    customer_id varchar(50) not null,
    description varchar(255) not null,
    status varchar(50) not null,
    primary key (id);
)


ALTER TABLE `snackbar`.`products`
ADD COLUMN `order_id` VARCHAR(45) NOT NULL AFTER `price`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`, `order_id`);
;


ALTER TABLE `snackbar`.`customers`
ADD COLUMN `order_id` VARCHAR(45) NOT NULL AFTER `last_name`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`, `order_id`);
;