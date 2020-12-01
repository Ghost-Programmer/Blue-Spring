

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



