--liquibase formatted sql

--changeset sagidullin.rr:2
create table client_type_v2
(
    id SERIAL PRIMARY KEY,
    typeName text
);


INSERT INTO client_type_v2
values (1, 'handyman'),
       (2, 'rancher');

ALTER TABLE client
    ADD client_type_v2 INT REFERENCES client_type_v2(id);

--changeset sagidullin.rr:3
update client
SET client_type_v2 = 1
    WHERE clienttype = 'handyman';

update client
SET client_type_v2 = 2
    WHERE clienttype = 'rancher';

ALTER TABLE client DROP clienttype