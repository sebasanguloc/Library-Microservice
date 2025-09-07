DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       mobile_number VARCHAR(20),
                       password VARCHAR(255) NOT NULL,
                       create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (id)
);

CREATE TABLE authorities (
                             id INT NOT NULL AUTO_INCREMENT,
                             user_id INT NOT NULL,
                             name VARCHAR(50) NOT NULL,
                             PRIMARY KEY (id),
                             FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO users (name, email, mobile_number, password) VALUES ('sebas','sebas@correo.com','123','{bcrypt}$2a$12$fsu5YsyzUxJ5WwrMEJWyv.aRQJ1WOfcuyaAJ5BNuCy.0QscaD.39u');
INSERT INTO users (name, email, mobile_number, password) VALUES ('laura','laura@correo.com','123','{bcrypt}$2a$12$fsu5YsyzUxJ5WwrMEJWyv.aRQJ1WOfcuyaAJ5BNuCy.0QscaD.39u');

SELECT * FROM users;

INSERT INTO authorities(user_id,name) VALUES(1,'ROLE_ADMIN');
INSERT INTO authorities(user_id,name) VALUES(2,'ROLE_USER');

SELECT * FROM authorities;