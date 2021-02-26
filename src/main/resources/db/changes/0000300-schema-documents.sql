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

