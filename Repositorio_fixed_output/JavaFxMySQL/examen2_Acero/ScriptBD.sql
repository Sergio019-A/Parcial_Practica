-- ================================================================
--  Script MySQL — examen2_Acero (Versión Limpia)
-- ================================================================

CREATE DATABASE IF NOT EXISTS examen2_Acero;
USE examen2_Acero;

-- IMPORTANTE: Borrar tablas en orden inverso a las FK para evitar errores
DROP TABLE IF EXISTS casa;
DROP TABLE IF EXISTS apartamento;
DROP TABLE IF EXISTS inmueble;
DROP TABLE IF EXISTS propietario;

-- ================================================================
-- TABLA 1 — propietario
-- ================================================================
CREATE TABLE propietario (
    id      VARCHAR(50)  NOT NULL,
    nombre  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

-- ================================================================
-- TABLA 2 — inmueble
-- ================================================================
CREATE TABLE inmueble (
    numero        BIGINT       NOT NULL,
    fecha_compra  DATE         NOT NULL,
    estado        TINYINT(1)   NOT NULL DEFAULT 1,
    propietario_id VARCHAR(50) NOT NULL,
    PRIMARY KEY (numero),
    CONSTRAINT fk_inmueble_propietario
        FOREIGN KEY (propietario_id)
        REFERENCES propietario (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = InnoDB;

-- ================================================================
-- TABLA 3 — apartamento
-- ================================================================
CREATE TABLE apartamento (
    numero      BIGINT NOT NULL,
    num_piso    INT    NOT NULL,
    PRIMARY KEY (numero),
    CONSTRAINT fk_apartamento_inmueble
        FOREIGN KEY (numero)
        REFERENCES inmueble (numero)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE = InnoDB;

-- ================================================================
-- TABLA 4 — casa
-- ================================================================
CREATE TABLE casa (
    numero          BIGINT NOT NULL,
    cant_pisos      INT    NOT NULL DEFAULT 1,
    PRIMARY KEY (numero),
    CONSTRAINT fk_casa_inmueble
        FOREIGN KEY (numero)
        REFERENCES inmueble (numero)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE = InnoDB;

-- ================================================================
-- Datos de prueba
-- ================================================================

INSERT INTO propietario (id, nombre) VALUES
    ('P001', 'Carlos Acero'),
    ('P002', 'Elena Rodriguez');

INSERT INTO inmueble (numero, fecha_compra, estado, propietario_id) VALUES
    (101101, '2025-01-15', 1, 'P001'),
    (202202, '2024-11-20', 1, 'P001'),
    (505505, '2026-02-10', 0, 'P002'),
    (303303, '2023-05-05', 1, 'P002');

INSERT INTO apartamento (numero, num_piso) VALUES
    (101101, 1),
    (505505, 5);

INSERT INTO casa (numero, cant_pisos) VALUES
    (202202, 2),
    (303303, 3);