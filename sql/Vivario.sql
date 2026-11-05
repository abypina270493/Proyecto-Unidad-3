-- Base de datos del proyecto Vivario (SQL Server)
CREATE DATABASE Vivario;
GO
USE Vivario;
GO

CREATE TABLE Planta (
    id             INT IDENTITY(1,1) PRIMARY KEY,
    nombre_comun   NVARCHAR(80)  NOT NULL,
    nombre_cient   NVARCHAR(120) NOT NULL,
    tipo           NVARCHAR(30)  NOT NULL,
    riego_dias     INT           NOT NULL,
    luz            NVARCHAR(20)  NOT NULL,
    precio         DECIMAL(8,2)  NOT NULL,
    fecha_registro DATE          NOT NULL
);
GO

-- Usuario (si ya creaste JavaUser en la práctica anterior, el LOGIN se omite)
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = 'JavaUser')
    CREATE LOGIN JavaUser WITH PASSWORD = 'password123';
GO

USE Vivario;
GO
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = 'JavaUser')
BEGIN
    CREATE USER JavaUser FOR LOGIN JavaUser;
END
ALTER ROLE db_datareader ADD MEMBER JavaUser;
ALTER ROLE db_datawriter ADD MEMBER JavaUser;
GO

INSERT INTO Planta (nombre_comun, nombre_cient, tipo, riego_dias, luz, precio, fecha_registro) VALUES
(N'Sábila',   N'Aloe vera',         N'Suculenta', 14, N'Alta', 80.00,  GETDATE()),
(N'Albahaca', N'Ocimum basilicum',  N'Aromática',  2, N'Alta', 35.00,  GETDATE()),
(N'Pothos',   N'Epipremnum aureum', N'Interior',   7, N'Baja', 120.00, GETDATE());
GO
