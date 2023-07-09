--liquibase formatted sql

--changeset sagidullin.rr:3

create type payment_system_enum as enum ('VISA', 'MASTERCARD', 'MIR', 'UNIONPAY');

--rollback drop type payment_system_enum

create table account
(
    id             bigserial primary key,
    handyman_user_id        bigint              not null,
    card_number    text                not null,
    payment_system payment_system_enum not null,
    foreign key (handyman_user_id) references users (id)
);

--rollback drop table account