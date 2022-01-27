-- create the databases
CREATE DATABASE IF NOT EXISTS crmdb;

-- create the users for each database
CREATE USER 'crm_user'@'%' IDENTIFIED BY 'crmPassword';
GRANT CREATE, ALTER, INDEX, LOCK TABLES, REFERENCES, UPDATE, DELETE, DROP, SELECT, INSERT ON `crmdb`.* TO 'crm_user'@'%';

FLUSH PRIVILEGES;