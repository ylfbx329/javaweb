drop database book;
create database book;
use book;

create table user
(
    un    varchar(10) not null
        primary key,
    pw    varchar(10) null,
    sex   varchar(4)  null,
    age   int         null,
    home  varchar(20) null,
    grade float(7, 2) null
);

create table book
(
    isbn      varchar(10) not null
        primary key,
    title     varchar(20) null,
    author    varchar(10) null,
    publisher varchar(10) null,
    year      int         null,
    price     float       null,
    classify  varchar(10) null,
    num       int         null
)