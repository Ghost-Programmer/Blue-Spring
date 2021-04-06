--liquibase formatted sql

--changeset jmiller:0000500

DROP SCHEMA IF EXISTS `organizations` ;

-- -----------------------------------------------------
-- Schema base
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `organizations` DEFAULT CHARACTER SET latin1 ;
USE `organizations` ;

CREATE TABLE `organizations`.`organizations` (
                                                 `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(255) NOT NULL,
                                                 `abbreviation` VARCHAR(20) NULL DEFAULT NULL,
                                                 `address_line_1` VARCHAR(100) NULL DEFAULT NULL,
                                                 `address_line_2` VARCHAR(100) NULL DEFAULT NULL,
                                                 `address_line_3` VARCHAR(100) NULL,
                                                 `country` BIGINT(20) UNSIGNED NULL,
                                                 `state` BIGINT(20) UNSIGNED NULL DEFAULT NULL,
                                                 `city` VARCHAR(80) NULL DEFAULT NULL,
                                                 `zip_code` VARCHAR(10) NULL DEFAULT NULL,
                                                 `phone_number` VARCHAR(15) NULL DEFAULT NULL,
                                                 `email_address` VARCHAR(80) NULL DEFAULT NULL,
                                                 `logo` BLOB NULL DEFAULT NULL,
                                                 `primary_color` VARCHAR(6) NULL,
                                                 `accent_color` VARCHAR(6) NULL,
                                                 PRIMARY KEY (`id`),
                                                 INDEX `idx_org_name` (`name` ASC) INVISIBLE,
                                                 INDEX `idx_org_abbre` (`abbreviation` ASC) VISIBLE);


INSERT INTO `organizations`.`organizations` (`name`, `abbreviation`, `address_line_1`, `address_line_2`, `city`, `zip_code`, `phone_number`) VALUES ('The Jockey Club', 'TJC', '821 Corporate Drive', '', 'Lexington', '40503', '8592242700');
INSERT INTO `organizations`.`organizations` (`name`, `abbreviation`, `address_line_1`, `city`, `zip_code`, `phone_number`, `email_address`) VALUES ('National Reining Horse Association', 'NRHA', '3021 W Reno Ave', 'Oklahoma City', '73107', '4059467400', 'memberships@nrha.com');
