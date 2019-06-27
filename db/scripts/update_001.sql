--таблица ролей
create table if not exists roles
(
    id   serial primary key,
    role varchar(200)
);
insert into roles(role)
values ('ADMIN'),
       ('USER');

--таблица пользователей
create table if not exists users
(
    id       serial primary key,
    name     varchar(200),
    login    varchar(200),
    role_id  integer references roles (id),
    password varchar(200)
);
insert into users(name, login, role_id, password)
values ('root', 'root', 1, 'root'), ('user', 'user', 2, 'user');

--таблица марок автомобилей
create table if not exists marka
(
    id   serial primary key,
    name varchar(200)
);
insert into marka(name)
values ('Toyota'),
       ('Nissan'),
       ('Honda');

--таблица моделей автомобилей
create table if not exists model
(
    id       serial primary key,
    name     varchar(200),
    marka_id integer references marka (id)
);
insert into model(name, marka_id)
values ('RAV', 1),
       ('Corolla', 1),
       ('Corolla Runx', 1),
       ('Corolla Alex', 1),
       ('Filder', 1),
       ('Carina', 1),
       ('Sunny', 2),
       ('Bluebird Sylphy', 2),
       ('Qashqai', 2),
       ('Skyline', 2),
       ('Civic', 3),
       ('Stream', 3),
       ('CR-V', 3);

--таблица коробок передач
create table if not exists transmission
(
    id   serial primary key,
    name varchar(200)
);
insert into transmission(name)
values ('АВТОМАТ'),
       ('МЕХАНИЧ'),
       ('ВАРИАТОР'),
       ('РОБОТ');

--таблица объявлений
create table if not exists announcement
(
    id          serial primary key,
    name        varchar(200),
    created_dat TIMESTAMP,
    user_id     integer references users (id),
    done        boolean
);
--таблица объекта автомобил
create table if not exists car
(
    id              serial primary key,
    description varchar(400),
    model_id        integer references model (id),
    transmission_id integer references transmission (id),
    yar             integer,
    announcement_id integer references announcement(id)
);
-- создание таблицы хранения фото
create table if not exists photo
(
    id     serial primary key,
    photo  bytea,
    car_id integer references car (id)
);