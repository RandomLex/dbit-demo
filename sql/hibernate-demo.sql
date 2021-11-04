create table car
(
    id           bigserial   not null,
    model        varchar(20) not null,
    release_date date        null
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
