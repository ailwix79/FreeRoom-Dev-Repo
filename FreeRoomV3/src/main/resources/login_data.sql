DROP TABLE IF EXISTS tableLogin;
CREATE TABLE tableLogin (userID VARCHAR(20) PRIMARY KEY, passwd VARCHAR(20), role VARCHAR(20));
INSERT INTO tableLogin (userID,passwd,role) VALUES ('professor','professor','Professor');
INSERT INTO tableLogin (userID,passwd,role) VALUES ('admin','admin','Administrative');
INSERT INTO tableLogin (userID,passwd,role) VALUES ('student','student','Student');