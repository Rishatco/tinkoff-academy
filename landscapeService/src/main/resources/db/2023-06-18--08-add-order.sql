--liquibase formatted sql

--changeset sagidulling.rr:5

create type work_type_enum as enum ('SHOVEL', 'PLANT', 'WATER', 'SOW');
create type status_enum as enum ('CREATED', 'IN_PROGRESS', 'DONE', 'APPROVED');

--rollback drop work_type_enum
--rollback drop status_enum

create table orders
(
    id         bigserial primary key,
    field_id   bigint                      not null,
    user_id    uuid                        not null,
    work_type  work_type_enum              not null,
    status     status_enum                 not null,
    created_at timestamp without time zone not null,
    foreign key (user_id) references client (id)
);

--rollback drop table orders