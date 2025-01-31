CREATE TABLE IF NOT EXISTS m_user(
	userId VARCHAR(50) PRIMARY KEY,
	mailAddress VARCHAR(50),
	name VARCHAR(50),
	password VARCHAR(500),
	role  VARCHAR(50),
	point INTEGER
);

CREATE TABLE IF NOT EXISTS m_faculty(
	faculty_id INT PRIMARY KEY,
	faculty_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS item(
	itemId VARCHAR(50) PRIMARY KEY,
	itemName VARCHAR(50),
	itemPrice INTEGER,
	ornerName VARCHAR(50),
	ornerId VARCHAR(50),
	message VARCHAR(50),
	inCart BOOLEAN,
	isSold BOOLEAN,
	salesDateTime TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_item(
	orderId VARCHAR(50) PRIMARY KEY,
	itemId VARCHAR(50),
	purchaserId VARCHAR(50),
	ornerId VARCHAR(50),
	priceAtOrder INTEGER,
	orderDateTime TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orders(
	orderId VARCHAR(50) PRIMARY KEY,
	itemId VARCHAR(50),
	purchaserId VARCHAR(50),
	ornerId VARCHAR(50),
	orderDateTime TIMESTAMP
);

CREATE TABLE IF NOT EXISTS notification(
	notificationId VARCHAR(50) PRIMARY KEY,
	itemId VARCHAR(50),
	ornerId VARCHAR(50),
	purchaserId VARCHAR(50),
	content VARCHAR(50),
	type VARCHAR(50),
	read_ BOOLEAN,
	dateTime_ TIMESTAMP
);

CREATE TABLE IF NOT EXISTS reservingAppt(
	reservingApptId VARCHAR(50) PRIMARY KEY,
	itemId VARCHAR(50),
	place1 VARCHAR(50),
	date1 VARCHAR(50),
	time1 VARCHAR(50),
	place2 VARCHAR(50),
	date2 VARCHAR(50),
	time2 VARCHAR(50),
	place3 VARCHAR(50),
	date3 VARCHAR(50),
	time3 VARCHAR(50),
	reservingApptDateTime TIMESTAMP
);