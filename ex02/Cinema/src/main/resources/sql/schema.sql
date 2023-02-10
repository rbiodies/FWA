-- DROP SCHEMA IF EXISTS cinema CASCADE;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS data;
DROP TABLE IF EXISTS images;

-- CREATE SCHEMA IF NOT EXISTS cinema;


CREATE TABLE IF NOT EXISTS users (
    id          INTEGER PRIMARY KEY,
    firstName   TEXT NOT NULL,
    lastName    TEXT NOT NULL,
    phoneNumber TEXT NOT NULL,
    email       TEXT NOT NULL,
    password    TEXT
);

CREATE TABLE IF NOT EXISTS data (
    id          INTEGER PRIMARY KEY,
    user_id     INTEGER NOT NULL REFERENCES users(id),
    date        TEXT NOT NULL,
    time        TIMESTAMP,
    ip          TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS images (
    id          INTEGER PRIMARY KEY,
    user_id     INTEGER NOT NULL REFERENCES users(id),
    filename    TEXT NOT NULL,
    size        TEXT NOT NULL,
    mime        TEXT NOT NULL,
    uniqueName  TEXT NOT NULL
);