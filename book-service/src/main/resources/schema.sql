DROP TABLE books;

CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50),
    price DOUBLE,
    stock INTEGER
);

INSERT INTO books (title, author, price, stock) VALUES ('El Quijote', 'Cervantes', 19.99, 10);
INSERT INTO books (title, author, price, stock) VALUES ('Cien Años de Soledad', 'García Márquez', 25.50, 5);
INSERT INTO books (title, author, price, stock) VALUES ('La Odisea', 'Homero', 15.75, 12);
INSERT INTO books (title, author, price, stock) VALUES ('La Iliada', 'Homero', 14.95, 7);
INSERT INTO books (title, author, price, stock) VALUES ('1984', 'George Orwell', 18.20, 9);
INSERT INTO books (title, author, price, stock) VALUES ('Rebelión en la Granja', 'George Orwell', 12.99, 14);
INSERT INTO books (title, author, price, stock) VALUES ('Fahrenheit 451', 'Ray Bradbury', 16.50, 8);
INSERT INTO books (title, author, price, stock) VALUES ('Crimen y Castigo', 'Dostoievski', 22.30, 6);
INSERT INTO books (title, author, price, stock) VALUES ('Los Hermanos Karamazov', 'Dostoievski', 24.99, 4);
INSERT INTO books (title, author, price, stock) VALUES ('Anna Karenina', 'Tolstoi', 21.80, 3);
INSERT INTO books (title, author, price, stock) VALUES ('Guerra y Paz', 'Tolstoi', 29.99, 2);
INSERT INTO books (title, author, price, stock) VALUES ('Orgullo y Prejuicio', 'Jane Austen', 17.45, 11);
INSERT INTO books (title, author, price, stock) VALUES ('Emma', 'Jane Austen', 16.75, 7);
INSERT INTO books (title, author, price, stock) VALUES ('Frankenstein', 'Mary Shelley', 14.30, 13);
INSERT INTO books (title, author, price, stock) VALUES ('Drácula', 'Bram Stoker', 15.60, 9);
INSERT INTO books (title, author, price, stock) VALUES ('El Principito', 'Antoine de Saint-Exupéry', 11.20, 25);
INSERT INTO books (title, author, price, stock) VALUES ('Don Juan Tenorio', 'José Zorrilla', 13.90, 5);
INSERT INTO books (title, author, price, stock) VALUES ('La Celestina', 'Fernando de Rojas', 12.50, 4);
INSERT INTO books (title, author, price, stock) VALUES ('La Divina Comedia', 'Dante Alighieri', 23.50, 6);
INSERT INTO books (title, author, price, stock) VALUES ('Hamlet', 'William Shakespeare', 18.40, 8);
INSERT INTO books (title, author, price, stock) VALUES ('Macbeth', 'William Shakespeare', 17.10, 10);
INSERT INTO books (title, author, price, stock) VALUES ('Romeo y Julieta', 'William Shakespeare', 16.80, 12);
INSERT INTO books (title, author, price, stock) VALUES ('Sueño de una Noche de Verano', 'William Shakespeare', 15.75, 6);
INSERT INTO books (title, author, price, stock) VALUES ('Moby Dick', 'Herman Melville', 20.50, 4);
INSERT INTO books (title, author, price, stock) VALUES ('La Cabaña del Tío Tom', 'Harriet Beecher Stowe', 14.95, 7);
INSERT INTO books (title, author, price, stock) VALUES ('Los Miserables', 'Victor Hugo', 26.30, 3);
INSERT INTO books (title, author, price, stock) VALUES ('Nuestra Señora de París', 'Victor Hugo', 21.70, 5);
INSERT INTO books (title, author, price, stock) VALUES ('Madame Bovary', 'Gustave Flaubert', 19.80, 6);
INSERT INTO books (title, author, price, stock) VALUES ('El Retrato de Dorian Gray', 'Oscar Wilde', 15.40, 9);
INSERT INTO books (title, author, price, stock) VALUES ('Ulises', 'James Joyce', 28.50, 2);
INSERT INTO books (title, author, price, stock) VALUES ('En Busca del Tiempo Perdido', 'Marcel Proust', 32.00, 1);
INSERT INTO books (title, author, price, stock) VALUES ('El Nombre de la Rosa', 'Umberto Eco', 22.10, 6);
INSERT INTO books (title, author, price, stock) VALUES ('El Padrino', 'Mario Puzo', 18.90, 10);
INSERT INTO books (title, author, price, stock) VALUES ('It', 'Stephen King', 25.40, 4);
INSERT INTO books (title, author, price, stock) VALUES ('El Resplandor', 'Stephen King', 23.00, 5);
INSERT INTO books (title, author, price, stock) VALUES ('Cementerio de Animales', 'Stephen King', 19.75, 6);
INSERT INTO books (title, author, price, stock) VALUES ('Carrie', 'Stephen King', 15.60, 8);
INSERT INTO books (title, author, price, stock) VALUES ('Apocalipsis', 'Stephen King', 27.50, 2);
INSERT INTO books (title, author, price, stock) VALUES ('Los Juegos del Hambre', 'Suzanne Collins', 18.40, 14);
INSERT INTO books (title, author, price, stock) VALUES ('En Llamas', 'Suzanne Collins', 19.10, 12);
INSERT INTO books (title, author, price, stock) VALUES ('Sinsajo', 'Suzanne Collins', 20.00, 11);
INSERT INTO books (title, author, price, stock) VALUES ('Harry Potter y la Piedra Filosofal', 'J.K. Rowling', 21.20, 20);
INSERT INTO books (title, author, price, stock) VALUES ('Harry Potter y la Cámara Secreta', 'J.K. Rowling', 22.10, 19);
INSERT INTO books (title, author, price, stock) VALUES ('Harry Potter y el Prisionero de Azkaban', 'J.K. Rowling', 23.40, 18);
INSERT INTO books (title, author, price, stock) VALUES ('Harry Potter y el Cáliz de Fuego', 'J.K. Rowling', 24.90, 17);
INSERT INTO books (title, author, price, stock) VALUES ('Harry Potter y la Orden del Fénix', 'J.K. Rowling', 26.20, 16);
INSERT INTO books (title, author, price, stock) VALUES ('Harry Potter y el Misterio del Príncipe', 'J.K. Rowling', 27.10, 15);
INSERT INTO books (title, author, price, stock) VALUES ('Harry Potter y las Reliquias de la Muerte', 'J.K. Rowling', 28.50, 14);
INSERT INTO books (title, author, price, stock) VALUES ('El Hobbit', 'J.R.R. Tolkien', 20.30, 13);
INSERT INTO books (title, author, price, stock) VALUES ('El Señor de los Anillos', 'J.R.R. Tolkien', 35.00, 10);
