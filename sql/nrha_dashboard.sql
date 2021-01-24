

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
DROP SCHEMA IF EXISTS `nrha_dashboard` ;

-- -----------------------------------------------------
-- Schema base
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nrha_dashboard` DEFAULT CHARACTER SET latin1 ;
USE `nrha_dashboard` ;

CREATE TABLE `nrha_dashboard`.`dashboard` (
                                              `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                              `user_id` BIGINT(20) UNSIGNED NOT NULL,
                                              `type_id` BIGINT(20) UNSIGNED NOT NULL,
                                              `rowspan` INT UNSIGNED NOT NULL,
                                              `colspan` INT UNSIGNED NOT NULL,
                                              `sort` INT UNSIGNED NOT NULL,
                                              `data` VARCHAR(255) NULL,
                                              PRIMARY KEY (`id`),
                                              UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                              INDEX `idx_user_id` (`user_id` ASC) VISIBLE);

CREATE TABLE `nrha_dashboard`.`dashboard_type` (
                                                   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                                   `type` VARCHAR(45) NOT NULL,
                                                   PRIMARY KEY (`id`),
                                                   UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                   UNIQUE INDEX `name_UNIQUE` (`type` ASC) VISIBLE);

ALTER TABLE `nrha_dashboard`.`dashboard`
    ADD INDEX `fk_dashboard_type_idx` (`type_id` ASC) VISIBLE;
;
ALTER TABLE `nrha_dashboard`.`dashboard`
    ADD CONSTRAINT `fk_dashboard_type`
        FOREIGN KEY (`type_id`)
            REFERENCES `nrha_dashboard`.`dashboard_type` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `fk_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `nrha_user`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
