--liquibase formatted sql

--changeset jmiller:0000200

DROP SCHEMA IF EXISTS `organizations`;

-- -----------------------------------------------------
-- Schema base
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `organizations` DEFAULT CHARACTER SET latin1;
USE `organizations`;

CREATE TABLE `organizations`.`organizations`
(
    `id`             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`           VARCHAR(255)        NOT NULL,
    `abbreviation`   VARCHAR(20)         NULL DEFAULT NULL,
    `address_line_1` VARCHAR(100)        NULL DEFAULT NULL,
    `address_line_2` VARCHAR(100)        NULL DEFAULT NULL,
    `address_line_3` VARCHAR(100)        NULL,
    `country`        BIGINT(20) UNSIGNED NULL,
    `state`          BIGINT(20) UNSIGNED NULL DEFAULT NULL,
    `city`           VARCHAR(80)         NULL DEFAULT NULL,
    `zip_code`       VARCHAR(10)         NULL DEFAULT NULL,
    `phone_number`   VARCHAR(15)         NULL DEFAULT NULL,
    `email_address`  VARCHAR(80)         NULL DEFAULT NULL,
    `logo`           BLOB                NULL DEFAULT NULL,
    `primary_color`  VARCHAR(6)          NULL,
    `accent_color`   VARCHAR(6)          NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_org_name` (`name` ASC) INVISIBLE,
    INDEX `idx_org_abbre` (`abbreviation` ASC) VISIBLE
);

ALTER TABLE `organizations`.`organizations`
    ADD INDEX `fk_state_idx` (`state` ASC) VISIBLE,
    ADD INDEX `fk_country_idx` (`country` ASC) VISIBLE;
;
ALTER TABLE `organizations`.`organizations`
    ADD CONSTRAINT `fk_country`
        FOREIGN KEY (`country`)
            REFERENCES `meta`.`country` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `fk_state`
        FOREIGN KEY (`state`)
            REFERENCES `meta`.`state` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;



INSERT INTO `organizations`.`organizations` (`name`, `abbreviation`, `address_line_1`, `address_line_2`, `city`,
                                             `zip_code`, `phone_number`)
VALUES ('MyMiller Consulting', 'MMC', '807 Pleasant St.', '', 'Paris', '40361', '8595331720');
