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