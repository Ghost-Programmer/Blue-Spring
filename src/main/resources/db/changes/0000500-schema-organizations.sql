--liquibase formatted sql

--changeset jmiller:0000500

DROP SCHEMA IF EXISTS `organizations` ;

-- -----------------------------------------------------
-- Schema base
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `organizations` DEFAULT CHARACTER SET latin1 ;
USE `organizations` ;
