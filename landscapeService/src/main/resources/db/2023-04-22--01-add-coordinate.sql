--liquibase formatted sql

--changeset sagidullin.rr:4

ALTER TABLE client ADD COLUMN latitude double precision;
ALTER TABLE client ADD COLUMN longitude double precision;
