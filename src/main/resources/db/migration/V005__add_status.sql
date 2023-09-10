ALTER TABLE accounts
    ADD COLUMN status TEXT;

UPDATE accounts
SET status = 'ACTIVE'
WHERE status IS NULL;

ALTER TABLE accounts
    ALTER COLUMN status SET NOT NULL;