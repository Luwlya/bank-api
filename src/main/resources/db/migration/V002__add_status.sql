ALTER TABLE customers
    ADD COLUMN status TEXT;

UPDATE customers
SET status = 'ACTIVE'
WHERE status IS NULL;

ALTER TABLE customers
    ALTER COLUMN status SET NOT NULL;