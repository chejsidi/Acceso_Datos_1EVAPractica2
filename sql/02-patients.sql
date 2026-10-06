CREATE TABLE patients (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(50)  NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    dni      VARCHAR(9)   NOT NULL,
    age      INT          NOT NULL,
    phone    VARCHAR(15),
    disease  VARCHAR(100)
);

INSERT INTO patients (name, lastname, dni, age, phone, disease)
VALUES ('Mikel', 'Etxeberria Ruiz', '11111111B', 45, '600111222', 'Hipertensión'),
       ('Laura', 'Sánchez Díaz', '22222222C', 32, '600333444', 'Arritmia');