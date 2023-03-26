--liquibase formatted sql

--changeset sagidullin.rr:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE client_type AS ENUM('handyman', 'rancher');

--rollback drop type client_type

CREATE TABLE client
(
    id           UUID DEFAULT uuid_generate_v4(),
    clientType   client_type,
    login        VARCHAR(255),
    email        VARCHAR(255),
    phone        VARCHAR(255),
    creationDate timestamp without time zone not null,
    updatingTime timestamp without time zone not null,
    PRIMARY KEY (id)
);
--rollback drop table client