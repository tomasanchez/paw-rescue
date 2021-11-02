-- Instancio un nuevo usuario administrador, (admin), con password admin
IF(SELECT EXISTS(SELECT 1 FROM mysql.user WHERE user = 'admin'))
 DROP USER 'admin'@'localhost';
FLUSH PRIVILEGES;
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

-- Permisos para el admin
GRANT SELECT ON * . * TO 'admin'@'localhost';
FLUSH PRIVILEGES;

-- Instancio una DB de testing
CREATE DATABASE IF NOT EXISTS test;
GRANT ALL PRIVILEGES ON `test` . * TO 'admin'@'localhost';
FLUSH PRIVILEGES;

-- Instancio una DB de QAs
CREATE DATABASE IF NOT EXISTS rescatepatitas;
GRANT ALL PRIVILEGES ON `rescatepatitas` . * TO 'admin'@'localhost';
FLUSH PRIVILEGES;

-- Instancio una DB de produccion;
CREATE DATABASE IF NOT EXISTS rescate_patitas;
GRANT ALL PRIVILEGES ON `rescate_patitas` . * TO 'admin'@'localhost';
FLUSH PRIVILEGES;
