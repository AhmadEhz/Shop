create table if not exists customer (
    id bigserial primary key ,
    name varchar,
    username varchar not null unique ,
    password varchar not null
);

create type order_status as enum (
    'PENDING','WAITING', 'ACCEPTED', 'REJECTED'
    );

create table if not exists orders (
    id bigserial primary key ,
    total_price int default 0,
    status order_status,
    product_numbers int default 0 check ( product_numbers between 0 and 5),
    customer_id bigint references customer(id)
);

create table if not exists product (
    id bigserial primary key ,
    name varchar not null ,
    category varchar not null ,
    type varchar,
    price int default 0,
    quantity int default 0,
    is_exist bool default false
);

create table if not exists product_order (
    id bigserial primary key ,
    order_id bigint references orders(id),
    product_id bigint references product(id),
    price int default 0,
    count int default 0
);