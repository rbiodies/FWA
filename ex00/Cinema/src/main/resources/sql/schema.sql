DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users (
    id          INTEGER PRIMARY KEY,
    firstName   TEXT NOT NULL,
    lastName    TEXT NOT NULL,
    phoneNumber TEXT NOT NULL,
    email       TEXT NOT NULL,
    password    TEXT
);