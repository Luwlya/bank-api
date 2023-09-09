CREATE table managers(
    id uuid primary key,
    status text,
    first_name text,
    last_name text,
    email text,
    password text,
    created_at timestamptz,
    updated_at timestamptz
);