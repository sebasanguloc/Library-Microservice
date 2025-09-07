DROP TABLE IF EXISTS users;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users(
                      id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                      first_name VARCHAR(255) NOT NULL,
                      last_name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) UNIQUE,
                      phone VARCHAR(20),
                      date_of_birth DATE,
                      address TEXT
);

INSERT INTO users (first_name, last_name, email, phone, date_of_birth, address) VALUES
                                                                                    ('Sebastian', 'Angulo', 'sebas@correo.com', '3200000000', '2004-12-13', 'Gran Granada, Bogota'),
                                                                                    ('Valentina', 'Rojas', 'valen.r@email.com', '3205551234', '1998-07-22', 'Carrera 7, Bogotá'),
                                                                                    ('Carlos', 'López', 'carlos.lopez@email.com', '3156667890', '1992-03-10', 'Avenida Principal, Medellín'),
                                                                                    ('Sofía', 'Gómez', 'sofia.g@email.com', '3187779900', '2001-09-05', 'Calle 100, Barranquilla'),
                                                                                    ('Andrés', 'Díaz', 'andres.d@email.com', '3108881122', '1985-11-30', 'Transversal 34, Cali'),
                                                                                    ('Isabella', 'Martínez', 'isa.martinez@email.com', '3129993344', '1993-01-19', 'Diagonal 54, Bucaramanga'),
                                                                                    ('Felipe', 'Torres', 'felipe.t@email.com', '3141115566', '1989-06-28', 'Calle 80, Cartagena'),
                                                                                    ('Camila', 'Vargas', 'camila.v@email.com', '3172228899', '1996-04-14', 'Avenida El Poblado, Envigado'),
                                                                                    ('Ricardo', 'Páez', 'ricardo.p@email.com', '3133334455', '1987-02-09', 'Carrera 15, Pereira'),
                                                                                    ('Lucía', 'Herrera', 'lucia.h@email.com', '3164446677', '2000-08-03', 'Calle 50, Manizales');
