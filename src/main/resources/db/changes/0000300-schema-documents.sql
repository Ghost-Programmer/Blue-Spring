--liquibase formatted sql

--changeset jmiller:0000300
DROP SCHEMA IF EXISTS `documents` ;

CREATE SCHEMA IF NOT EXISTS `documents` DEFAULT CHARACTER SET latin1 ;
USE `documents` ;

CREATE TABLE IF NOT EXISTS documents (
                                         `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                         `uuid` VARCHAR(45) NOT NULL,
                                         `user_id` BIGINT(20) UNSIGNED NOT NULL,
                                         `filename` VARCHAR(255) NOT NULL,
                                         `content_type` VARCHAR(255) NULL,
                                         `document` MEDIUMBLOB NULL,
                                         `size` BIGINT(20) UNSIGNED NOT NULL,
                                         `date_created` TIMESTAMP NOT NULL,
                                         `last_updated` TIMESTAMP NULL,
                                         PRIMARY KEY (`id`),
                                         UNIQUE INDEX `documents_uuid_unique_idx` (`uuid` ASC) INVISIBLE,
                                         INDEX `documents_uuid_idx` (`uuid` ASC) VISIBLE,
                                         INDEX `fk_idx_documents_user_id_idx` (`user_id` ASC) VISIBLE,
                                         INDEX `idx_filename_user_id` (`user_id` ASC, `filename` ASC) VISIBLE,
                                         CONSTRAINT `fk_idx_documents_user_id`
                                             FOREIGN KEY (`user_id`)
                                                 REFERENCES `user`.`user` (`id`)
                                                 ON DELETE NO ACTION
                                                 ON UPDATE NO ACTION);

ALTER TABLE `documents`.`documents`
    ADD INDEX `doc_idx_id_user_id` (`id` ASC, `user_id` ASC) INVISIBLE,
    ADD INDEX `doc_idx_search` (`filename` ASC, `date_created` ASC, `size` ASC, `content_type` ASC) VISIBLE;
ALTER TABLE `documents`.`documents` ALTER INDEX `fk_idx_documents_user_id_idx` INVISIBLE;

CREATE TABLE `documents`.`pages` (
                                     `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                     `uuid` VARCHAR(45) NOT NULL,
                                     `name` VARCHAR(255) NOT NULL,
                                     `page` BLOB NOT NULL,
                                     `date_created` TIMESTAMP NOT NULL,
                                     `last_updated` TIMESTAMP NULL DEFAULT NULL,
                                     PRIMARY KEY (`id`),
                                     INDEX `idx_page_pk` (`id` ASC) INVISIBLE,
                                     INDEX `idx_page_uuid` (`uuid` ASC) VISIBLE);

CREATE TABLE `documents`.`page_access` (
                                           `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                           `page_id` BIGINT(20) UNSIGNED NOT NULL,
                                           `role_id` BIGINT(20) UNSIGNED NOT NULL,
                                           `date_created` TIMESTAMP NOT NULL,
                                           `last_updated` TIMESTAMP NULL DEFAULT NULL,
                                           PRIMARY KEY (`id`),
                                           INDEX `idx_page_access_page_id` (`page_id` ASC) INVISIBLE,
                                           INDEX `idx_page_access_role_id` (`role_id` ASC) INVISIBLE,
                                           CONSTRAINT `fk_idx_page_access_page_id`
                                               FOREIGN KEY (`page_id`)
                                                   REFERENCES `documents`.`pages` (`id`)
                                                   ON DELETE NO ACTION
                                                   ON UPDATE NO ACTION,
                                           CONSTRAINT `fk_idx_page_access_role_id`
                                               FOREIGN KEY (`page_id`)
                                                   REFERENCES `user`.`security_role` (`id`)
                                                   ON DELETE NO ACTION
                                                   ON UPDATE NO ACTION);
