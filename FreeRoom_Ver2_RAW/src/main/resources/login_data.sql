DROP TABLE IF EXISTS tableLogin;
CREATE TABLE tableLogin (userID VARCHAR(20) PRIMARY KEY, passwd VARCHAR(20), role VARCHAR(20));
INSERT INTO tableLogin (userID,passwd,role) VALUES ('professor','1234','Professor');