CREATE DATABASE csbp;

CREATE TABLE Empleado (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    identificacion VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    primerApellido VARCHAR(255) NOT NULL,
    segundoApellido VARCHAR(255),
    fechaNacimiento DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    active TINYINT(1) DEFAULT 1,

    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;