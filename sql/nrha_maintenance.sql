

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema base
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `nrha_maintenance` ;

-- -----------------------------------------------------
-- Schema base
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nrha_maintenance` DEFAULT CHARACTER SET latin1 ;
USE `nrha_maintenance` ;

CREATE TABLE `scheduled` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(255) NOT NULL,
    `start` DATETIME(6) NOT NULL,
    `end` DATETIME(6) NOT NULL,
    `date_created` TIMESTAMP NULL DEFAULT NULL,
    `last_updated` TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);


CREATE TABLE `nrha_maintenance`.`audit_table` (
                                                  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                                  `type` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
                                                  `table_name` VARCHAR(255) NOT NULL,
                                                  `user` VARCHAR(255) NOT NULL,
                                                  `row_id` VARCHAR(255) NOT NULL,
                                                  `recorded` TIMESTAMP NOT NULL,
                                                  `date_created` TIMESTAMP NULL DEFAULT NULL,
                                                  `last_updated` TIMESTAMP NULL DEFAULT NULL,
                                                  PRIMARY KEY (`id`),
                                                  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                  INDEX `idx_table` (`table_name` ASC) VISIBLE,
                                                  INDEX `idx_user` (`user` ASC) INVISIBLE,
                                                  INDEX `idx_table_row` (`table_name` ASC, `row_id` ASC) VISIBLE);

CREATE TABLE `nrha_maintenance`.`audit_row` (
                                                 `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                                 `audit_table_id` BIGINT(20) UNSIGNED NOT NULL,
                                                 `field` VARCHAR(255) NOT NULL,
                                                 `original` LONGTEXT NOT NULL,
                                                 `new_value` LONGTEXT NULL,
                                                 PRIMARY KEY (`id`),
                                                 UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                 INDEX `idx_audit_table_id` (`audit_table_id` ASC) INVISIBLE,
                                                 CONSTRAINT `fk_audit_row_audit_table_id`
                                                 FOREIGN KEY (`audit_table_id`)
                                                 REFERENCES `audit_table` (`id`)
                                                 ON DELETE NO ACTION
                                                 ON UPDATE NO ACTION);


