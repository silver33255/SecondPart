DROP TABLE IF EXISTS film_session;

CREATE TABLE film_session(
id INT AUTO_INCREMENT,
date DATE,
start TIME,
film VARCHAR(255),
free_seats INT DEFAULT 35,
PRIMARY KEY(id)
);