-- Crear BD
CREATE DATABASE p_cine
CHARSET utf8mb4
COLLATE utf8mb4_spanish2_ci;
-- Activar BD
USE p_cine;
-- Crear tabla directores
CREATE TABLE directores (
    idDirector INT AUTO_INCREMENT,
    nombreDirector VARCHAR(45) NOT NULL,
    apellidosDirector VARCHAR(120) NOT NULL,
    nacionalidadDirector VARCHAR(45),
    PRIMARY KEY (idDirector)
);
-- Crear tabla peliculas
CREATE TABLE peliculas (
    idPelicula INT AUTO_INCREMENT,
    tituloPelicula VARCHAR(45) NOT NULL,
    generoPelicula VARCHAR(45) NOT NULL,
    fechaEstrenoPelicula DATE NOT NULL,
    idDirectorFK INT NOT NULL,
    PRIMARY KEY (idPelicula),
    FOREIGN KEY (idDirectorFK)
        REFERENCES directores (idDirector)
);
-- Crear tabla actores
CREATE TABLE actores (
    idActor INT AUTO_INCREMENT,
    nombreActor VARCHAR(45) NOT NULL,
    apellidosActor VARCHAR(120) NOT NULL,
    salarioActor DECIMAL(9 , 2 ) NOT NULL,
    PRIMARY KEY (idActor)
);
-- Crear tabla peliculas_actores
CREATE TABLE peliculas_actores (
    idPeliculasActores INT AUTO_INCREMENT,
    idPeliculaFK INT NOT NULL,
    idActorFK INT NOT NULL,
    PRIMARY KEY (idPeliculasActores),
    FOREIGN KEY (idPeliculaFK)
        REFERENCES peliculas (idPelicula),
    FOREIGN KEY (idActorFK)
        REFERENCES actores (idActor)
);
-- Crear tabla usuarios
CREATE TABLE usuarios (
    idUsuario INT AUTO_INCREMENT,
    nombreUsuario VARCHAR(45) NOT NULL,
    claveUsuario VARCHAR(256) NOT NULL,
    tipoUsuario VARCHAR(45) NOT NULL,
    PRIMARY KEY (idUsuario)
);
-- Crear usuario Select, Insert, Delete y Update (CRUD) localhost
CREATE USER 'studium'@'localhost' IDENTIFIED BY 'Studium2025#';
GRANT SELECT, INSERT, DELETE, UPDATE ON p_cine.* TO 'studium'@'localhost';
FLUSH PRIVILEGES;