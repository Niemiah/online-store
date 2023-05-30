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