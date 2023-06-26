--liquibase formatted sql

--changeset sagdullin.rr:2
create extension if not exists postgis;

create table field
(
    id          bigserial primary key,
    gardener_id bigint           not null,
    address     text             not null,
    latitude    double precision not null,
    longitude   double precision not null,
    area        geometry         not null,
    constraint fk_field_gardener foreign key (gardener_id) references gardener (id)
);
--rollback drop table field