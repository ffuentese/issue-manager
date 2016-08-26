-- Ejecutar para crear el usuario de la tabla

CREATE USER 'issue_admin'@'localhost' IDENTIFIED BY 'issue';
GRANT ALL PRIVILEGES ON issuemanager.* TO 'issue_admin'@'localhost';
