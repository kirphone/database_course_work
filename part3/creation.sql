create table product_category
(
    name varchar(31) primary key
);

create table status_order
(
    name varchar(31) primary key,
    description text
);

create table account
(
	id serial primary key,
	name varchar(63) not null,
	phone varchar(31) unique not null,
	email varchar(63) unique not null,
	password varchar(63) not null
);

create table shop_company
(
	name varchar(31) primary key
);

create table discount_card
(
	id serial primary key,
	description text,
	expired_date date,
	shop_company_name varchar(31) not null references shop_company on update cascade on delete cascade,
	account_id int not null references account on update cascade on delete cascade
);

create table shop
(
	id serial primary key,
	name varchar(31),
	address_lat float not null,
    address_lng float not null,
	company_name varchar(31) references shop_company on update cascade on delete restrict
);

create table product
(
	id serial primary key,
	name varchar(255) not null,
	description text,
	category varchar(31) references product_category on update cascade on delete set null
);

create table product_shop
(
	product_id int references product on update cascade on delete restrict,
	shop_id int references shop on update cascade on delete cascade,
	price real not null,
	primary key (product_id, shop_id)
);

create table "order"
(
	id serial primary key,
	address_lat float not null,
    address_lng float not null,
	shop_id int not null references shop on update cascade on delete cascade,
	customer_id int not null references account on update cascade on delete cascade,
	courier_id int references account on update cascade on delete cascade,
	status varchar(31) not null references status_order on update restrict on delete restrict
);

create table message
(
	id serial primary key,
	message_text text not null,
	sender_id int not null references account on update cascade on delete cascade,
	order_id int not null references "order" on update cascade on delete cascade,
	send_datetime timestamp with time zone not null
);

create table order_product
(
	order_id int references "order" on update cascade on delete cascade,
	product_id int references product on update cascade on delete restrict,
	price real not null,
	product_count int not null,
	need_confirm boolean not null,
	primary key (order_id, product_id)
);

create table review
(
	id serial primary key,
	order_id int unique not null references "order" on update cascade on delete cascade,
	description text,
	rate int not null
);