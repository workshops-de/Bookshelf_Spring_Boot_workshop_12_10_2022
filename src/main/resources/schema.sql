create table if not exists book
(
    id          identity not null primary key,
    title       varchar(255),
    description varchar(255),
    author      varchar(255),
    isbn        varchar(255) not null
);
