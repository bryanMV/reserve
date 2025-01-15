
-- Tabla Clientes
CREATE TABLE Cliente (
    cedula VARCHAR(100) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100),
    telefono VARCHAR(15) NOT NULL
);

-- Tabla Horarios
CREATE TABLE Horario (
    hora TIME  PRIMARY KEY
);

-- Tabla Reservaciones
CREATE TABLE Reserva (
    id VARCHAR(100) PRIMARY KEY,
    cliente_cedula BIGINT,
    horario_hora BIGINT,
    fecha DATE NOT NULL,
    FOREIGN KEY (cliente_cedula) REFERENCES cliente(cedula),
    FOREIGN KEY (horario_hora) REFERENCES horario(hora),
    CONSTRAINT unq_reservacion UNIQUE(horario_hora, fecha) -- Restriccion no debe haber dos reservas con el mismo horario y fecha
);

-- Agregar Horarios
INSERT INTO Horario values('1:00:00');
INSERT INTO Horario values('14:00:00');
INSERT INTO Horario values('18:00:00');

-- Agregar Cliente
INSERT INTO Cliente values('1', 'Carlos','Carlos@correo','3121231223');
INSERT INTO Cliente values('2', 'Jorge','Jorge@correo','1234567890');
INSERT INTO Cliente values('3', 'Aura','Aura@correo','1111111111');

-- Buscar Horario disponible por dia
SELECT h.hora
FROM Horario h
LEFT JOIN Reserva r ON h.hora = r.horario_hora AND r.fecha = '2025-01-15'
WHERE r.id IS NULL;

-- Buscar Reserva por id
SELECT r
FROM Reserva r
WHERE r.id = '1';

-- Comprobar si existe un cliente opcion 1
SELECT EXISTS (
    SELECT 1
    FROM Cliente
    WHERE id = '4'
) AS existe;

-- Comprobar si existe un cliente opcion 2
SELECT COUNT(*) 
FROM Cliente 
WHERE id = '4';

-- Actualizar una reservación existente
UPDATE Reserva
SET fecha = '2025-01-20', horario_hora = '18:00:00'
WHERE id = 1;

-- Cancelar una reservación
DELETE FROM Reserva WHERE id = 1;

-- Tablas
SELECT *
FROM Cliente;

SELECT *
FROM Horario;

SELECT *
FROM Reservacion;

