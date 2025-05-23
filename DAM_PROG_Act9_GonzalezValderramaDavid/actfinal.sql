drop database if exists GameStop;
create database if not exists GameStop;
use GameStop;

CREATE TABLE juegos (
    id_juego INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    genero varchar(100),
    pegi int,
    precio decimal(10,2),
    stock INT
);


CREATE TABLE trabajadores (
    id_trabajador INT AUTO_INCREMENT PRIMARY KEY,
    usuario varchar(100),
    contrasena varchar(100)
    );
    

CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(100),
    apellidos varchar(100),
    correo varchar(100),
    telefono varchar(100),
    numerosocio varchar(10)
);

CREATE TABLE ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    fecha date,
    id_juego INT,
    id_cliente INT,
    id_trabajador INT,
    cantidad int,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente) on delete cascade,
    FOREIGN KEY (id_juego) REFERENCES juegos(id_juego) on delete cascade,
    FOREIGN KEY (id_trabajador) REFERENCES trabajadores(id_trabajador) on delete cascade
);

INSERT INTO juegos (nombre, genero, pegi, precio, stock) VALUES
('The Legend of Zelda: Breath of the Wild', 'Aventura', 12, 59.99, 10),
('FIFA 24', 'Deportes', 3, 69.99, 20),
('Grand Theft Auto V', 'Acción', 18, 29.99, 15),
('Minecraft', 'Sandbox', 7, 19.99, 25),
('Elden Ring', 'RPG', 16, 59.99, 12),
('Call of Duty: Modern Warfare II', 'Shooter', 18, 69.99, 18),
('Animal Crossing: New Horizons', 'Simulación', 3, 49.99, 14),
('God of War Ragnarök', 'Acción', 18, 69.99, 9),
('Mario Kart 8 Deluxe', 'Carreras', 3, 59.99, 22),
('Cyberpunk 2077', 'RPG', 18, 39.99, 10),
('Hogwarts Legacy', 'Aventura', 16, 59.99, 13),
('Resident Evil 4 Remake', 'Terror', 18, 49.99, 11),
('NBA 2K24', 'Deportes', 3, 69.99, 17),
('The Sims 4', 'Simulación', 12, 39.99, 20),
('Assassin’s Creed Valhalla', 'Acción', 18, 44.99, 8),
('Super Smash Bros. Ultimate', 'Lucha', 12, 59.99, 16),
('Red Dead Redemption 2', 'Acción', 18, 39.99, 13),
('Among Us (Edición física)', 'Multijugador', 7, 5.99, 30),
('Street Fighter 6', 'Lucha', 16, 59.99, 10),
('Final Fantasy XVI', 'RPG', 16, 69.99, 7);


insert into trabajadores (usuario, contrasena)
values ('admin','admin');

select * from trabajadores;

select * from juegos;
