create database agregator;

create table post
(
    id   serial ,
    name varchar(255),
    text text,
    link varchar(255),
    created timestamp,
    primary key (id, text)
);