--liquibase formatted sql

--changeset jmiller:0000000

USE `user` ;

-- -----------------------------------------------------
-- Populate Tables
-- -----------------------------------------------------


INSERT INTO `user`.`oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `autoapprove`)
VALUES ('appClient', 'nrha', '$2a$10$Ob7Xv7QF3MnrTyIgV6J7Qu5T89zxaHJlT0i7aYW876hizquvUYg.m', 'read,write', 'password,authorization_code,check_token,refresh_token,client_credentials', 'http://localhost:8080', 'ROLE_CLIENT', '1800', '172800', 1);

INSERT INTO `user`.`security_role` (`authority`, `name`, `category`, `description`, `default`, `date_created`) VALUES ('ROLE_USER', 'User', 'User', 'Grants Access to Login', 1, now());
INSERT INTO `user`.`security_role` (`authority`, `name`, `category`, `description`, `default`, `date_created`) VALUES ('ROLE_USER_MANAGEMENT', 'User Management', 'Admin', 'Modify User Role Access.', 0, now());
INSERT INTO `user`.`security_role` (`authority`, `name`, `category`, `description`, `default`, `date_created`) VALUES ('ROLE_CHANGE_USER_PASSWORD', 'Change User Password', 'Admin', 'Permission to change a password for a user.', 0, now());
INSERT INTO `user`.`security_role` (`authority`, `name`, `category`, `description`, `default`, `date_created`) VALUES ('ROLE_CHANGE_OWN_PASSWORD', 'Change Own Password', 'User', 'Permission to change own password.', 1, now());
INSERT INTO `user`.`security_role` (`authority`, `name`, `category`, `description`, `default`, `date_created`) VALUES ('ROLE_DEVELOPER', 'Developer', 'Dev', 'Developer special access.', 0, now());
INSERT INTO `user`.`security_role` (`authority`, `name`, `category`, `description`, `default`, `date_created`) VALUES ('ROLE_MONITOR', 'System Monitor', 'Dev', 'Monitor System status.', 0, now());
INSERT INTO `user`.`security_role` (`authority`, `name`, `category`, `description`, `default`, `date_created`) VALUES ('ROLE_APPROVER', 'Document Approved', 'Staff', 'Approves Proof Documents', 0, now());
INSERT INTO `user`.`security_role` (`authority`, `name`, `category`, `description`, `default`, `date_created`) VALUES ('ROLE_DOCUMENT_MANAGER', 'Document Manager', 'Staff', 'Manage Document database.', 0, now());
INSERT INTO `user`.`security_role` (`authority`, `name`, `category`, `description`, `default`, `date_created`) VALUES ('ROLE_ADMIN_MAINTENANCE', 'Maintenance Administration', 'Admin', 'Manage Maintenance Settings.', 0, now());
INSERT INTO `user`.`security_role` (`authority`, `name`, `category`, `description`, `default`, `date_created`) VALUES ('ROLE_ADMIN_QUARTZ', 'Quartz Job Scheduling', 'Admin', 'Manage Quartz Job Scheduling.', 0, now());
