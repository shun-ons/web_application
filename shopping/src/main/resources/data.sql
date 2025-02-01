INSERT INTO m_user(userId, mailAddress,name,password,role,point)
VALUES('u01', 'system@co.jp','システム管理者','$2a$10$fA3FpACA13sFAyVRrku3JOdBZ3CdtZ6RrZC499VATw1n9Q0ZQMHnG','ROLE_ADMIN',1000),
('u02', 'kanko@co.jp','Jan','$2a$10$fA3FpACA13sFAyVRrku3JOdBZ3CdtZ6RrZC499VATw1n9Q0ZQMHnG','ROLE_GENERAL',1000),
('u03', 'onishi@co.jp', 'Onishi', '$2a$10$fA3FpACA13sFAyVRrku3JOdBZ3CdtZ6RrZC499VATw1n9Q0ZQMHnG','ROLE_GENERAL',1000);

INSERT INTO item(itemId, itemName, itemPrice, ornerName, ornerId, message, inCart, isSold, isCompletion, salesDateTime)
VALUES('p01', 'スプリング入門', 1000, 'Jan','u02' , '買ってね', FALSE, TRUE, FALSE,'2024-12-20 23:50:06'),
('p02', 'TCP/IP', 1000, 'Onishi', 'u03', '状態良好です.', FALSE, TRUE, FALSE,'2024-12-23 22:44:14'),
('p03', '信号とシステム', 3000, 'Onishi', 'u03', '難しいです.', FALSE, TRUE, FALSE,'2024-12-29 23:47:09'),
('p04', '微積分', 500, 'Onishi', 'u03', '少し古いです,', TRUE, FALSE, TRUE,'2025-01-18 23:38:41');

Insert INTO order_item(orderId, itemId, purchaserId, ornerId, priceAtOrder, orderDateTime)
VALUES('oi01', 'p03', 'u01', 'u03', 3000, '2024-12-29 23:56:26');

INSERT INTO notification(notificationId, itemId, ornerId, purchaserId, content, type, read_, dateTime_)
VALUES('n01', 'p04', 'u03', 'u02', 'Janさんが「微積分」を購入しました!!', 'call', FALSE, '2025-01-18 23:40:13');

INSERT INTO orders(orderId, itemId, purchaserId, ornerId, orderDateTime)
VALUES('o01', 'p04', 'u02', 'u03', '2025-01-18 23:40:13');

INSERT INTO reservingAppt(reservingApptId, itemId, place1, date1, time1, place2, date2, time2, place3, date3, time3, reservingApptDateTime)
VALUES('r01', 'p04', 'システム工学部A棟前', '2025-01-31', '09:00', 'システム工学部A棟前', '2025-01-31', '09:00', 'システム工学部A棟前', '2025-01-31', '09:00', '2025-01-31 20:14:50');