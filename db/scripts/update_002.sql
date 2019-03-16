create table if not exists  halls(
id serial primary key,
place integer,
row integer
);
create table if not exists  accounts(
id serial primary key,
name varchar(200),
telephone varchar(20),
halls_id
);