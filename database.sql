-- ============================================================
--  Script de creación de Base de Datos - db_rrhh_donbosco
--  Sistema de Gestión de Recurso Humano
--  Universidad Don Bosco — Desafío 1 DWF
-- ============================================================

CREATE DATABASE IF NOT EXISTS db_rrhh_donbosco
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE db_rrhh_donbosco;

-- -------------------------------------------------------
-- Tabla: Departamento
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS Departamento (
    idDepartamento INT AUTO_INCREMENT PRIMARY KEY,
    nombreDepartamento VARCHAR(50) NOT NULL,
    descripcionDepartamento TEXT
);

-- -------------------------------------------------------
-- Tabla: Empleados
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS Empleados (
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY,
    numeroDui VARCHAR(10) NOT NULL UNIQUE COMMENT 'Formato ########-#',
    nombrePersona VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    numeroTelefono VARCHAR(9),
    correoInstitucional VARCHAR(50),
    fechaNacimiento DATE
);

-- -------------------------------------------------------
-- Tabla: Cargos
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS Cargos (
    idCargo INT AUTO_INCREMENT PRIMARY KEY,
    cargo VARCHAR(50) NOT NULL,
    descripcionCargo TEXT,
    jefatura BOOLEAN NOT NULL DEFAULT FALSE
);

-- -------------------------------------------------------
-- Tabla: TipoContratacion
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS TipoContratacion (
    idTipoContratacion INT AUTO_INCREMENT PRIMARY KEY,
    tipoContratacion VARCHAR(100) NOT NULL
);

-- -------------------------------------------------------
-- Tabla: Contrataciones (tabla central con FK a las demás)
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS Contrataciones (
    idContratacion INT AUTO_INCREMENT PRIMARY KEY,
    idDepartamento INT NOT NULL,
    idEmpleado INT NOT NULL,
    idCargo INT NOT NULL,
    idTipoContratacion INT NOT NULL,
    fechaContratacion DATE NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_cont_departamento FOREIGN KEY (idDepartamento) REFERENCES Departamento(idDepartamento),
    CONSTRAINT fk_cont_empleado FOREIGN KEY (idEmpleado) REFERENCES Empleados(idEmpleado),
    CONSTRAINT fk_cont_cargo FOREIGN KEY (idCargo) REFERENCES Cargos(idCargo),
    CONSTRAINT fk_cont_tipocontratacion FOREIGN KEY (idTipoContratacion) REFERENCES TipoContratacion(idTipoContratacion)
);

-- -------------------------------------------------------
-- Datos de prueba
-- -------------------------------------------------------
INSERT INTO Departamento (nombreDepartamento, descripcionDepartamento) VALUES
('Tecnología', 'Departamento de desarrollo y sistemas informáticos'),
('Recursos Humanos', 'Gestión del personal y bienestar laboral'),
('Finanzas','Contabilidad, presupuesto y auditoría'),
('Académico', 'Coordinación y planificación académica');

INSERT INTO TipoContratacion (tipoContratacion) VALUES
('Tiempo Completo'),
('Medio Tiempo'),
('Por Honorarios'),
('Contrato Temporal');

INSERT INTO Cargos (cargo, descripcionCargo, jefatura) VALUES
('Director General', 'Máxima autoridad ejecutiva de la institución', TRUE),
('Jefe de Departamento', 'Responsable y coordinador de un departamento', TRUE),
('Desarrollador', 'Desarrollo y mantenimiento de software', FALSE),
('Docente', 'Impartición de clases universitarias', FALSE),
('Asistente', 'Apoyo administrativo y operativo', FALSE);

INSERT INTO Empleados (numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento) VALUES
('12345678-9', 'Juan Carlos Pérez', 'jperez', '2234-5678', 'jperez@udb.edu.sv', '1990-05-15'),
('98765432-1', 'María González López', 'mgonzalez', '2234-5679', 'mgonzalez@udb.edu.sv', '1985-08-22'),
('45678912-3', 'Carlos Martínez Ruiz', 'cmartinez', '2234-5680', 'cmartinez@udb.edu.sv', '1992-03-10'),
('11223344-5', 'Ana Sofía Ramírez', 'aramirez',  '2234-5681', 'aramirez@udb.edu.sv', '1995-11-30');

INSERT INTO Contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado) VALUES
(1, 1, 3, 1, '2022-01-15', 1200.00, TRUE),
(2, 2, 2, 1, '2020-06-01', 1800.00, TRUE),
(4, 3, 4, 2, '2021-09-01', 900.00, TRUE),
(3, 4, 5, 4, '2023-03-15', 700.00, FALSE);