--liquibase formatted sql

--changeset jmiller:0000100

DROP SCHEMA IF EXISTS `dashboard` ;

CREATE SCHEMA IF NOT EXISTS `dashboard` DEFAULT CHARACTER SET latin1 ;
USE `dashboard` ;

CREATE TABLE `dashboard`.`dashboard` (
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

CREATE TABLE `dashboard`.`dashboard_type` (
                                                   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                                   `type` VARCHAR(45) NOT NULL,
                                                   `security_role_id` BIGINT(20) UNSIGNED NOT NULL,
                                                   `rowspan` INT UNSIGNED NOT NULL,
                                                   `colspan` INT UNSIGNED NOT NULL,
                                                   `data` VARCHAR(255) NULL,
                                                   `default` BIT(1) NULL DEFAULT 0,
                                                   PRIMARY KEY (`id`),
                                                   UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                   UNIQUE INDEX `name_UNIQUE` (`type` ASC) VISIBLE);

ALTER TABLE `dashboard`.`dashboard`
    ADD INDEX `fk_dashboard_type_idx` (`type_id` ASC) VISIBLE;
;
ALTER TABLE `dashboard`.`dashboard`
    ADD CONSTRAINT `fk_dashboard_type`
        FOREIGN KEY (`type_id`)
            REFERENCES `dashboard`.`dashboard_type` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `fk_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `dashboard`.`dashboard_type`
    ADD CONSTRAINT `fk_dashboard_type_security_role_id`
        FOREIGN KEY (`security_role_id`)
            REFERENCES `user`.`security_role` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `dashboard`.`dashboard_type`
    ADD INDEX `dt_default_index` (`default` ASC) INVISIBLE;
ALTER TABLE `dashboard`.`dashboard_type` ALTER INDEX `name_UNIQUE` INVISIBLE;



INSERT INTO `dashboard`.`dashboard_type` (`type`, `security_role_id`, `rowspan`, `colspan`, `default`) VALUES ('USER_INFO', '1', '1', '1', 1);
INSERT INTO `dashboard`.`dashboard_type` (`type`, `security_role_id`, `rowspan`, `colspan`, `default`) VALUES ('VM_MEMORY_MONITOR', '6', '1', '2', 0);
INSERT INTO `dashboard`.`dashboard_type` (`type`, `security_role_id`, `rowspan`, `colspan`, `default`) VALUES ('CPU_MONITOR', '6', '1', '2', 0);
INSERT INTO `dashboard`.`dashboard_type` (`type`, `security_role_id`, `rowspan`, `colspan`, `default`) VALUES ('LIVE_VM_MEMORY_MONITOR', '6', '1', '1', 1);
INSERT INTO `dashboard`.`dashboard_type` (`type`, `security_role_id`, `rowspan`, `colspan`, `default`) VALUES ('LIVE_CPU_MONITOR', '6', '1', '1', 1);
INSERT INTO `dashboard`.`dashboard_type` (`type`, `security_role_id`, `rowspan`, `colspan`, `default`) VALUES ('LIVE_MEMORY_MONITOR', '6', '1', '1', 1);
INSERT INTO `dashboard`.`dashboard_type` (`type`, `security_role_id`, `rowspan`, `colspan`, `default`) VALUES ('VM_SETTINGS', '6', '1', '1', 0);
