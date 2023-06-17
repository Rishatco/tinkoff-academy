--liquibase formatted sql

--changeset sagidullin.rr:2


create table skill
(
    id      bigserial primary key,
    handyman_user_id bigint not null,
    name    text   not null,
    foreign key (handyman_user_id) references handyman_user (id)
);
--rollback drop table skill