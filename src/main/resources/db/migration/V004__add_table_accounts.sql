CREATE table accounts
(
    id          UUID PRIMARY KEY NOT NULL,
    customer_id UUID             NOT NULL REFERENCES customers (id),
    name        TEXT             NOT NULL,
    balance     NUMERIC          NOT NULL,
    currency    TEXT             NOT NULL,
    created_at  TIMESTAMPTZ      NOT NULL,
    updated_at  TIMESTAMPTZ      NOT NULL
);

CREATE table transactions
(
    id                UUID PRIMARY KEY NOT NULL,
    debit_account_id  UUID             NOT NULL REFERENCES accounts (id),
    credit_account_id UUID             NOT NULL REFERENCES accounts (id),
    amount            NUMERIC          NOT NULL,
    description       TEXT             NOT NULL,
    created_at        TIMESTAMPTZ      NOT NULL
);