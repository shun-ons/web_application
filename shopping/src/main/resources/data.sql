INSERT INTO m_user(userId, mailAddress,name,password,role,point)
VALUES('u01', 'system@co.jp','システム管理者','$2a$10$fA3FpACA13sFAyVRrku3JOdBZ3CdtZ6RrZC499VATw1n9Q0ZQMHnG','ROLE_ADMIN',1000),
('u02', 'kanko@co.jp','Jan','$2a$10$fA3FpACA13sFAyVRrku3JOdBZ3CdtZ6RrZC499VATw1n9Q0ZQMHnG','ROLE_GENERAL',1000),
('u03', 'onishi@co.jp', 'Onishi', '$2a$10$fA3FpACA13sFAyVRrku3JOdBZ3CdtZ6RrZC499VATw1n9Q0ZQMHnG','ROLE_GENERAL',1000);

INSERT INTO item(itemId, itemName, itemPrice, ornerName, ornerId, message, salesDateTime, isSold)
VALUES('P01', 'スプリング入門', 1000, 'Jan','u02' , '買ってね', '2024-12-20 23:50:06', TRUE),
('P02', 'TCP/IP', 1000, 'Onishi', 'u03', '状態良好です.', '2024-12-23 22:44:14', TRUE),
('P03', '信号とシステム', 3000, 'Onishi', 'u03', '難しいです.', '2024-12-29 23:47:09', FALSE);

Insert INTO order_item(orderId, itemId, purchaserId, ornerId, priceAtOrder, orderDateTime)
VALUES('o01', 'p03', 'u01', 'u03', 3000, '2024-12-29 23:56:26');