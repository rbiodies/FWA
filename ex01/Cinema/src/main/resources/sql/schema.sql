-- DROP SCHEMA IF EXISTS cinema CASCADE;
DROP TABLE IF EXISTS users;

-- CREATE SCHEMA IF NOT EXISTS cinema;


CREATE TABLE IF NOT EXISTS users (
    id          SERIAL PRIMARY KEY,
    firstName   TEXT NOT NULL,
    lastName    TEXT NOT NULL,
    phoneNumber TEXT NOT NULL,
    email       TEXT NOT NULL,
    password    TEXT
);