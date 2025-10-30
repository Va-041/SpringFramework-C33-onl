create table users
(
    id       serial
        constraint users_pk
            primary key,
    username varchar not null,
    email    varchar not null
);

alter table users
    owner to postgres;