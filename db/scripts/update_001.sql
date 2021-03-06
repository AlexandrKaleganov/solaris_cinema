create table if not exists  halls(
id serial primary key,
row integer,
place integer
);
insert into halls(row, place) values(1,1), (1,2), (1,3),(2,1), (2,2), (2,3),(3,1), (3,2), (3,3);
create table if not exists  accounts(
id serial primary key,
name varchar(200),
telephone varchar(20)
);
create table if not exists purchased_seats(
id serial primary key,
accounts_id integer references accounts(id),
halls_id integer references halls(id) not null unique
);