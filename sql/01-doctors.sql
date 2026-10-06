CREATE TABLE doctors (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50)    NOT NULL,
    lastname   VARCHAR(100)   NOT NULL,
    dni        VARCHAR(9)     NOT NULL,
    salary     DECIMAL(10, 2) NOT NULL,
    speciality VARCHAR(50)    NOT NULL
);

INSERT INTO doctors (name, lastname, dni, salary, speciality)
VALUES ('Juan', 'Perez', '12345678A', 3500.00, 'Cardiología');