ALTER TABLE Users ADD COLUMN phone_number VARCHAR(10);
ALTER TABLE Products MODIFY COLUMN product_price DECIMAL(8, 2);
ALTER TABLE Orders DROP COLUMN user_id;
ALTER TABLE Shipping_Methods RENAME TO Delivery_Methods;
ALTER TABLE Invoices ADD COLUMN invoice_total DECIMAL(8, 2);