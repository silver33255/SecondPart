DROP TABLE IF EXISTS film_session;
DROP TABLE IF EXISTS user;

CREATE TABLE film_session(
id INT AUTO_INCREMENT,
date DATE,
start TIME,
film VARCHAR(255),
PRIMARY KEY(id)
);

INSERT INTO film_session (date, start, film) VALUES
(NOW(), '09:00:00', 'Medal of honor'),
(NOW(), '10:30:00', 'The Dark Knight'),
(NOW(), '14:00:00', 'Schindler''s List'),
(NOW(), '19:00:00', 'The Lord of the Rings');

CREATE TABLE user (
login VARCHAR(50) NOT NULL PRIMARY KEY,
password VARCHAR(60) NOT NULL,
authority VARCHAR(50) NOT NULL
);

INSERT INTO user (login, password, authority) VALUES
('admin', '$2a$10$VzAOigLaEgJLPiSCWmx.4.5CnbgBiVf1gsI3fKhZZNDEWOa8.cz8q', 'ROLE_ADMIN'),
('user', '$2a$10$6lDBuQYNV048CgvItA5QaOdGj6mSDnasuBIwkr.zEEmFQY57N6UmS', 'ROLE_USER');
