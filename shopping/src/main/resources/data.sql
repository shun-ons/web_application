INSERT INTO m_user(userId, mailAddress,name,password)
VALUES('u01', 'system@co.jp','Tom','aaabb'),
('u02', 'kanko@co.jp','Jan','cccdd'),
('u03', 'onishi@co.jp', 'Onishi', 'eeeff');

INSERT INTO m_faculty(faculty_id,faculty_name)
VALUES(1,'システム工学部'),
(2,'観光学部');

INSERT INTO item(itemId, itemName, itemPrice, ornerName, message, salesDateTime, isSold)
VALUES('P01', 'スプリング入門', 1000, 'Onishi', '買ってね', '2024-12-20 23:50:06', TRUE),
('P02', 'TCP/IP', 1000, 'Onishi', '状態良好です.', '2024-12-23 22:44:14', TRUE),
('P03', '信号とシステム', 3000, 'Onishi', '難しいです.', '2024-12-29 23:47:09', FALSE);

Insert INTO order_item(orderId, itemId, purchaserId, ornerId, priceAtOrder, orderDateTime)
VALUES('o01', 'p03', 'u01', 'u03', 3000, '2024-12-29 23:56:26');