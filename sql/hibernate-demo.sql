create table car
(
    id           bigserial   not null,
    model        varchar(20) not null,
    release_date date null
);


create table report
(
    "name"    varchar(20) not null,
    "type"    varchar(20) not null,
    recipient varchar(20) not null,
    constraint report_pk primary key (name, "type")
);

create table audio_system
(
    music_power int,
    music_name  varchar(20) not null,
    speakers    int
);

create table person
(
    id     bigserial   not null,
    "name" varchar(20) not null
);

create table credentials
(
    person_id  int8,
    login      varchar(20) not null,
    "password" varchar(20) not null
);

create table post
(
    id     bigserial   not null,
    "name" varchar(20) not null
);

create table tag
(
    id     bigserial   not null,
    "name" varchar(20) not null
);

--drop table post_tag;
create table post_tag
(
    post_id int8 null,
    tag_id  int8 null
);

--mapped

create table bird
(
    id      bigserial   not null,
    origin  varchar(20) not null,
    flyable bool null,
    growing varchar(20) null
);

create table fish
(
    id       bigserial   not null,
    origin   varchar(20) not null,
    skeleton varchar(20) null,
    poisoned bool null
);

--single table

create table animal
(
    id         bigserial   not null,
    origin     varchar(20) not null,
    flyable    bool null,
    growing    varchar(20) null,
    skeleton   varchar(20) null,
    poisoned   bool null,
    class_name varchar(20) not null
);

--join

create table animal
(
    id         bigserial   not null,
    origin     varchar(20) not null,
    class_name varchar(20) not null
);

create table bird
(
    id      int8 not null,
    flyable bool null,
    growing varchar(20) null
);

create table fish
(
    id       int8 not null,
    skeleton varchar(20) null,
    poisoned bool null
);

--per table
create table bird
(
    id      bigserial   not null,
    origin  varchar(20) not null,
    flyable bool null,
    growing varchar(20) null
);

create table fish
(
    id       bigserial   not null,
    origin   varchar(20) not null,
    skeleton varchar(20) null,
    poisoned bool null
);

create table sequence_table
(
    name    varchar(20),
    last_id int8
);

create table product (
                         id bigserial not null,
                         "name" varchar(20) not null,
                         price int4,
                         product_type int8 null
);

create table product_type (
                              id bigserial not null,
                              "name" varchar(20) not null
);

insert into product_type(name) values('Computer');
insert into product_type(name) values('Smartphone');

insert into product(name, price, product_type) values('Apple', 2000, 1);
insert into product(name, price, product_type) values('Asus', 1200, 1);
insert into product(name, price, product_type) values('HP', 1500, 1);
insert into product(name, price, product_type) values('Samsung', 1000, 2);
insert into product(name, price, product_type) values('Xiaomi', 700, 2);