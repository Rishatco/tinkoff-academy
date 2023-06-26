--liquibase formatted sql

--changeset sagidullin.rr:1

create table users
(
    id         bigserial,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    phone      varchar(255),
    photo      bytea,
    primary key (id)
);

--rollback drop table users

