CREATE TABLE IF NOT EXISTS m_user(
	mailAddress VARCHAR(50) PRIMARY KEY,
	name VARCHAR(50),
	password VARCHAR(20)
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
	message VARCHAR(50),
	salesDateTime TIMESTAMP,
	isSold BOOLEAN
);