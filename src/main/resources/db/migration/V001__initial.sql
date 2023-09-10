CREATE TABLE customers
(
    id         UUID PRIMARY KEY NOT NULL,
    first_name TEXT             NOT NULL,
    last_name  TEXT             NOT NULL,
    email      TEXT             NOT NULL UNIQUE,
    address    TEXT             NOT NULL,
    phone      TEXT             NOT NULL,
    created_at TIMESTAMPTZ      NOT NULL,
    updated_at TIMESTAMPTZ      NOT NULL
);