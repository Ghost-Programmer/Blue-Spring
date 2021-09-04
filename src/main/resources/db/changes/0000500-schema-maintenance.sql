--liquibase formatted sql

--changeset jmiller:0000500

DROP SCHEMA IF EXISTS `maintenance` ;

-- -----------------------------------------------------
-- Schema base
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `maintenance` DEFAULT CHARACTER SET latin1 ;
USE `maintenance` ;

CREATE TABLE `scheduled` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(255) NOT NULL,
    `start` DATETIME(6) NOT NULL,
    `end` DATETIME(6) NOT NULL,
    `date_created` TIMESTAMP NULL DEFAULT NULL,
    `last_updated` TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);


CREATE TABLE `maintenance`.`audit_table` (
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

CREATE TABLE `maintenance`.`audit_row` (
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


ALTER TABLE `maintenance`.`scheduled`
    ADD INDEX `scheduled_idx_start_date` (`start` ASC) INVISIBLE,
    ADD INDEX `scheduled_idx_start_end` (`start` ASC, `end` ASC) VISIBLE;
;

CREATE TABLE `maintenance`.`settings` (
                                          `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                          `setting` VARCHAR(255) NOT NULL,
                                          `organization` BIGINT UNSIGNED NULL,
                                          `value` VARCHAR(255) NOT NULL,
                                          `start_date_time` DATETIME(6) NULL,
                                          `end_date_time` DATETIME(6) NULL,
                                          `date_created` TIMESTAMP NULL DEFAULT NULL,
                                          `last_updated` TIMESTAMP NULL DEFAULT NULL,
                                          PRIMARY KEY (`id`),
                                          INDEX `idx_setting` (`setting` ASC) INVISIBLE,
                                          INDEX `idx_lookup` (`setting` ASC, `start_date_time` ASC, `end_date_time` ASC) VISIBLE);


ALTER TABLE `maintenance`.`settings`
    ADD CONSTRAINT `fk_settings_organization`
        FOREIGN KEY (`organization`)
            REFERENCES `organizations`.`organizations` (`id`)
            ON DELETE NO ACTION
               ON UPDATE NO ACTION;

CREATE TABLE `maintenance`.`event` (
                                       `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                       `event` BLOB NOT NULL,
                                       `date_created` TIMESTAMP NOT NULL,
                                       `last_updated` TIMESTAMP NOT NULL,
                                       INDEX `idx_event_date_created` (`date_created` DESC) VISIBLE,
                                       PRIMARY KEY (`id`));
