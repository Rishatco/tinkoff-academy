--liquibase formatted sql

--changeset sagidullin.rr:1
create table gardener
(
    id         bigserial primary key,
    first_name text not null,
    last_name  text not null,
    email      text not null,
    phone      text
);
--rollback drop table gardener