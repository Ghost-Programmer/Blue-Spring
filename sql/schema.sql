DROP SCHEMA IF EXISTS `documents` ;
DROP SCHEMA IF EXISTS `dashboard` ;
DROP SCHEMA IF EXISTS `maintenance` ;
DROP SCHEMA IF EXISTS `user` ;
DROP SCHEMA IF EXISTS `quartz`;
DROP SCHEMA IF EXISTS `meta`;
drop SCHEMA IF EXISTS `organizations`;


SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


CREATE SCHEMA IF NOT EXISTS `user` DEFAULT CHARACTER SET latin1 ;

use user;
-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
                                      `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                      `uuid` VARCHAR(45) NOT NULL,
                                      `username` VARCHAR(100) NULL,
                                      `password` VARCHAR(128) NULL,
                                      `enabled` BIT(1) NOT NULL DEFAULT 1,
                                      `account_expired` BIT(1) NULL DEFAULT 0,
                                      `password_change_required` BIT(1) NOT NULL DEFAULT 0,
                                      `account_locked` BIT(1) NULL DEFAULT 0,
                                      `account_verified` BIT(1) NOT NULL DEFAULT 0,
                                      `account_complete` BIT(1) NOT NULL DEFAULT 0,
                                      `account_refreshed` BIT(1) NOT NULL DEFAULT 0,
                                      `date_created` TIMESTAMP NULL DEFAULT NULL,
                                      `last_updated` TIMESTAMP NULL,
                                      UNIQUE INDEX `user_uuid_unique_idx` (`uuid` ASC) INVISIBLE,
									  INDEX `user_uuid_idx` (`uuid` ASC) VISIBLE,
                                      PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `security_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `security_role` (
                                               `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                               `authority` VARCHAR(45) NOT NULL,
                                               `name` VARCHAR(100) NULL,
                                               `category` VARCHAR(100) NULL,
                                               `description` VARCHAR(255) NULL,
                                               `default` BIT(1) NULL DEFAULT 1,
                                               `date_created` TIMESTAMP NULL DEFAULT NULL,
                                               `last_updated` TIMESTAMP NULL,
                                               PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_security_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_security_role` (
                                                    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                    `user_id` BIGINT(20) UNSIGNED NOT NULL,
                                                    `security_role_id` BIGINT(20) UNSIGNED NOT NULL,
                                                    `date_created` TIMESTAMP NULL DEFAULT NULL,
                                                    `last_updated` TIMESTAMP NULL,
                                                    INDEX `user` (`user_id` ASC),
                                                    INDEX `security_role` (`security_role_id` ASC),
                                                    PRIMARY KEY (`id`),
                                                    CONSTRAINT `user`
                                                        FOREIGN KEY (`user_id`)
                                                            REFERENCES `user` (`id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION,
                                                    CONSTRAINT `security_role`
                                                        FOREIGN KEY (`security_role_id`)
                                                            REFERENCES `security_role` (`id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `verification_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `verification_token` (
                                                    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                                    `email` VARCHAR(80) NOT NULL,
                                                    `verification` VARCHAR(80) NOT NULL,
                                                    `password` VARCHAR(80) NULL,
                                                    `expiration` TIMESTAMP NULL,
                                                    `valid` BIT(1) NULL DEFAULT 1,
                                                    `date_created` TIMESTAMP NULL,
                                                    `last_updated` TIMESTAMP NULL,
                                                    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


create table IF NOT EXISTS oauth_access_token (
                                                  token_id VARCHAR(255),
                                                  token BLOB,
                                                  authentication_id VARCHAR(255),
                                                  user_name VARCHAR(255),
                                                  client_id VARCHAR(255),
                                                  authentication BLOB,
                                                  refresh_token VARCHAR(255)
);

create table IF NOT EXISTS oauth_client_details (
                                                    client_id VARCHAR(255) PRIMARY KEY,
                                                    resource_ids VARCHAR(255),
                                                    client_secret VARCHAR(255),
                                                    scope VARCHAR(255),
                                                    authorized_grant_types VARCHAR(255),
                                                    web_server_redirect_uri VARCHAR(255),
                                                    authorities VARCHAR(255),
                                                    access_token_validity INTEGER,
                                                    refresh_token_validity INTEGER,
                                                    additional_information VARCHAR(4096),
                                                    autoapprove tinyint
);

create table IF NOT EXISTS oauth_refresh_token (
                                                   token_id VARCHAR(255),
                                                   token BLOB,
                                                   authentication BLOB
);
