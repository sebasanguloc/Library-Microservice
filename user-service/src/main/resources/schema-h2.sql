DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT RANDOM_UUID(),
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE,
                       phone VARCHAR(20),
                       date_of_birth DATE,
                       address TEXT
);
