CREATE table customers(
    id uuid primary key,
    first_name text,
    last_name text,
    email text,
    address text,
    phone text,
    created_at timestamptz,
    updated_at timestamptz
);