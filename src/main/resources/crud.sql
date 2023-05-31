INSERT INTO Users (first_name, last_name, email) VALUES ('Tony', 'Smith', 'tony@example.com');
INSERT INTO Products (product_name, product_description, product_price) VALUES ('Red Shoes', 'Vintage red shoes.', 59.99);
INSERT INTO Categories (category_name) VALUES ('Shoes');
INSERT INTO Product_Categories (product_id, category_id) VALUES (1, 1);
INSERT INTO Orders (user_id) VALUES (1);
INSERT INTO Order_Details (order_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO Shipping_Methods (method_name, shipping_cost, shipping_time) VALUES ('Express', 10.00, '2 days');
INSERT INTO Addresses (user_id, address_line1, city, state, postal_code, country) VALUES (1, '123 Main St', 'Denver', 'CO', '80120', 'USA');
INSERT INTO Payments (user_id, card_number, expiration_date) VALUES (1, '1234123412341234', '2026-04-04');
INSERT INTO Invoices (order_id, user_id, payment_id) VALUES (1, 1, 1);

UPDATE Users SET first_name='Stalone' WHERE user_id=1;
UPDATE Products SET product_price=69.99 WHERE product_id=1;
UPDATE Categories SET category_name='Shirts' WHERE category_id=1;
UPDATE Order_Details SET quantity=3 WHERE order_id=1 AND product_id=1;
UPDATE Shipping_Methods SET shipping_cost=12.00 WHERE shipping_id=1;
UPDATE Addresses SET city='Chicago' WHERE address_id=1;
UPDATE Payments SET card_number='4321432143214321' WHERE payment_id=1;
UPDATE Invoices SET user_id=2 WHERE invoice_id=1;
UPDATE Inventory SET stock=20 WHERE inventory_id=1;
UPDATE Reviews SET rating=4 WHERE review_id=1;

DELETE FROM Reviews WHERE review_id=1;
DELETE FROM Inventory WHERE inventory_id=1;
DELETE FROM Invoices WHERE invoice_id=1;
DELETE FROM Payments WHERE payment_id=1;
DELETE FROM Addresses WHERE address_id=1;
DELETE FROM Shipping_Methods WHERE shipping_id=1;
DELETE FROM Order_Details WHERE order_id=1 AND product_id=1;
DELETE FROM Orders WHERE order_id=1;
DELETE FROM Product_Categories WHERE product_id=1 AND category_id=1;
DELETE FROM Categories WHERE category_id=1;
DELETE FROM Products WHERE product_id=1;
DELETE FROM Users WHERE user_id=1;

ALTER TABLE Users ADD COLUMN phone_number VARCHAR(10);
ALTER TABLE Products MODIFY COLUMN product_price DECIMAL(8, 2);
ALTER TABLE Orders DROP COLUMN user_id;
ALTER TABLE Shipping_Methods RENAME TO Delivery_Methods;
ALTER TABLE Invoices ADD COLUMN invoice_total DECIMAL(8, 2);

SELECT *
FROM Users u
JOIN Orders o ON u.user_id=o.user_id
JOIN Order_Details od ON o.order_id=od.order_id
JOIN Products p ON od.product_id=p.product_id
JOIN Product_Categories pc ON p.product_id=pc.product_id
JOIN Categories c ON pc.category_id=c.category_id
JOIN Shipping_Methods sm ON o.order_id=sm.shipping_id
JOIN Addresses a ON u.user_id=a.user_id
JOIN Payments pay ON u.user_id=pay.user_id
JOIN Invoices i ON o.order_id=i.order_id AND u.user_id=i.user_id;

SELECT u.first_name, u.last_name, o.order_id
FROM Users u
INNER JOIN Orders o ON u.user_id=o.user_id;

SELECT u.first_name, u.last_name, a.address_line1
FROM Users u
LEFT JOIN Addresses a ON u.user_id=a.user_id;

SELECT u.first_name, u.last_name, p.product_name
FROM Users u
RIGHT JOIN Orders o ON u.user_id=o.user_id
JOIN Order_Details od ON o.order_id=od.order_id
JOIN Products p ON od.product_id=p.product_id;

SELECT u.first_name, u.last_name, o.order_id
FROM Users u
LEFT JOIN Orders o ON u.user_id=o.user_id
UNION
SELECT u.first_name, u.last_name, o.order_id
FROM Users u
RIGHT JOIN Orders o ON u.user_id=o.user_id;

SELECT u1.first_name, u2.first_name AS 'friend'
FROM Users u1
JOIN Users u2 ON u1.user_id != u2.user_id;

SELECT u.user_id, COUNT(o.order_id) AS order_count
FROM Users u
JOIN Orders o ON u.user_id=o.user_id
GROUP BY u.user_id;

SELECT p.product_id, AVG(r.rating) AS average_rating
FROM Products p
JOIN Reviews r ON p.product_id=r.product_id
GROUP BY p.product_id;

SELECT c.category_id, SUM(p.product_price) AS total_product_price
FROM Categories c
JOIN Product_Categories pc ON c.category_id=pc.category_id
JOIN Products p ON pc.product_id=p.product_id
GROUP BY c.category_id;

SELECT a.state, COUNT(u.user_id) AS user_count
FROM Addresses a
JOIN Users u ON a.user_id=u.user_id
GROUP BY a.state;

SELECT o.order_id, SUM(p.product_price * od.quantity) AS order_total
FROM Orders o
JOIN Order_Details od ON o.order_id=od.order_id
JOIN Products p ON od.product_id=p.product_id
GROUP BY o.order_id;

SELECT u.user_id, MIN(o.order_id) AS first_order_id
FROM Users u
JOIN Orders o ON u.user_id=o.user_id
GROUP BY u.user_id;

SELECT p.product_id, MAX(od.quantity) AS max_quantity_ordered
FROM Products p
JOIN Order_Details od ON p.product_id=od.product_id
GROUP BY p.product_id;

SELECT u.user_id, COUNT(o.order_id) AS order_count
FROM Users u
JOIN Orders o ON u.user_id=o.user_id
GROUP BY u.user_id
HAVING order_count > 2;

SELECT p.product_id, AVG(r.rating) AS average_rating
FROM Products p
JOIN Reviews r ON p.product_id=r.product_id
GROUP BY p.product_id
HAVING average_rating >= 4.1;

SELECT c.category_id, SUM(p.product_price) AS total_product_price
FROM Categories c
JOIN Product_Categories pc ON c.category_id=pc.category_id
JOIN Products p ON pc.product_id=p.product_id
GROUP BY c.category_id
HAVING total_product_price > 800;

SELECT a.state, COUNT(u.user_id) AS user_count
FROM Addresses a
JOIN Users u ON a.user_id=u.user_id
GROUP BY a.state
HAVING user_count > 40;

SELECT o.order_id, SUM(p.product_price * od.quantity) AS order_total
FROM Orders o
JOIN Order_Details od ON o.order_id=od.order_id
JOIN Products p ON od.product_id=p.product_id
GROUP BY o.order_id
HAVING order_total > 250;

SELECT u.user_id, MIN(o.order_id) AS first_order_id
FROM Users u
JOIN Orders o ON u.user_id=o.user_id
GROUP BY u.user_id
HAVING first_order_id < 50;

SELECT p.product_id, MAX(od.quantity) AS max_quantity_ordered
FROM Products p
JOIN Order_Details od ON p.product_id=od.product_id
GROUP BY p.product_id
HAVING max_quantity_ordered > 5;