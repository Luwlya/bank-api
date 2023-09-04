ALTER TABLE customers ADD COLUMN status TEXT;
UPDATE customers SET status = 'ACTIVE' WHERE status IS NULL;