--liquibase formatted sql

--changeset jmiller:0000150

DROP SCHEMA IF EXISTS `meta`;

-- -----------------------------------------------------
-- Schema base
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `meta` DEFAULT CHARACTER SET latin1;
USE `meta`;


CREATE TABLE `meta`.`country`
(
    `id`             BIGINT UNSIGNED   NOT NULL AUTO_INCREMENT,
    `code`           CHAR(3)           NOT NULL,
    `code_short`     CHAR(2)           NULL     DEFAULT NULL,
    `name`           VARCHAR(80)       NOT NULL,
    `active`         BIT(1)            NOT NULL,
    `hemisphere`     ENUM ('N', 'S')   NOT NULL,
    `sort`           SMALLINT UNSIGNED NULL     DEFAULT NULL,
    `state_required` BIT(1)            NOT NULL DEFAULT b'1',
    `date_created`   TIMESTAMP         NULL     DEFAULT NULL,
    `last_updated`   TIMESTAMP         NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_country_code` (`code` ASC) VISIBLE
);

CREATE TABLE `meta`.`state`
(
    `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `country_id`   BIGINT UNSIGNED NOT NULL,
    `code`         CHAR(3)         NOT NULL,
    `name`         VARCHAR(80)     NULL DEFAULT NULL,
    `active`       BIT(1)          NULL DEFAULT b'1',
    `sort`         SMALLINT        NULL DEFAULT NULL,
    `date_created` TIMESTAMP       NULL DEFAULT NULL,
    `last_updated` TIMESTAMP       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_country_idx` (`country_id` ASC) VISIBLE,
    CONSTRAINT `fk_country_id`
        FOREIGN KEY (`country_id`)
            REFERENCES `meta`.`country` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

INSERT INTO `country`
VALUES (1, 'ABW', 'AW', 'Aruba', _binary '', 'N', 120, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (2, 'AFG', 'AF', 'Afghanistan', _binary '', 'N', 10, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (3, 'AGO', 'AO', 'Angola', _binary '', 'S', 60, _binary '\0', '2011-01-17 23:13:39', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (4, 'AIA', 'AI', 'Anguilla', _binary '', 'N', 70, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (5, 'ALB', 'AL', 'Albania', _binary '', 'N', 20, _binary '\0', '2011-01-17 23:13:39', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (6, 'AND', 'AD', 'Andorra', _binary '', 'N', 50, _binary '\0', '2011-01-17 23:13:39', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (7, 'ANT', 'AN', 'Netherlands Antilles', _binary '', 'N', 1510, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (8, 'ARE', 'AE', 'United Arab Emirates', _binary '', 'N', 2220, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (9, 'ARG', 'AR', 'Argentina', _binary '', 'S', 100, _binary '\0', '2011-01-17 23:13:39', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (10, 'ARM', 'AM', 'Armenia', _binary '', 'N', 110, _binary '\0', '2011-01-17 23:13:39', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (11, 'ASM', 'AS', 'American Samoa', _binary '', 'S', 40, _binary '\0', '2011-01-17 23:13:39',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (12, 'ATA', 'AQ', 'Antarctica', _binary '', 'N', 80, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (13, 'ATF', 'TF', 'French Southern Territories', _binary '', 'N', 780, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (14, 'ATG', 'AG', 'Antigua And Barbuda', _binary '', 'N', 90, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (15, 'AUS', 'AU', 'Australia', _binary '', 'S', 130, _binary '\0', '2011-01-17 23:13:39',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (16, 'AUT', 'AT', 'Austria', _binary '', 'N', 140, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (17, 'AZE', 'AZ', 'Azerbaijan', _binary '', 'N', 150, _binary '\0', '2011-01-17 23:13:39',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (18, 'BDI', 'BI', 'Burundi', _binary '', 'S', 350, _binary '\0', '2011-01-17 23:13:39', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (19, 'BEL', 'BE', 'Belgium', _binary '', 'N', 210, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (20, 'BEN', 'BJ', 'Benin', _binary '', 'N', 230, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (21, 'BFA', 'BF', 'Burkina Faso', _binary '', 'N', 340, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (22, 'BGD', 'BD', 'Bangladesh', _binary '', 'N', 180, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (23, 'BGR', 'BG', 'Bulgaria', _binary '', 'N', 330, _binary '\0', '2011-01-17 23:13:39', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (24, 'BHR', 'BH', 'Bahrain', _binary '', 'N', 170, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (25, 'BHS', 'BS', 'Bahamas', _binary '', 'N', 160, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (26, 'BIH', 'BA', 'Bosnia And Herzegowina', _binary '', 'N', 270, _binary '\0', '2011-01-17 23:13:39',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (27, 'BLR', 'BY', 'Belarus', _binary '', 'N', 200, _binary '\0', '2011-01-17 23:13:39', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (28, 'BLZ', 'BZ', 'Belize', _binary '', 'N', 220, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (29, 'BMU', 'BM', 'Bermuda', _binary '', 'N', 240, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (30, 'BOL', 'BO', 'Bolivia', _binary '', 'S', 260, _binary '\0', '2011-01-17 23:13:39', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (31, 'BRA', 'BR', 'Brazil', _binary '', 'S', 300, _binary '\0', '2011-01-17 23:13:39', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (32, 'BRB', 'BB', 'Barbados', _binary '', 'N', 190, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (33, 'BRN', 'BN', 'Brunei Darussalam', _binary '', 'N', 320, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (34, 'BTN', 'BT', 'Bhutan', _binary '', 'N', 250, _binary '\0', '2011-01-17 23:13:39', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (35, 'BVT', 'BV', 'Bouvet Island', _binary '', 'N', 290, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (36, 'BWA', 'BW', 'Botswana', _binary '', 'S', 280, _binary '\0', '2011-01-17 23:13:39', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (37, 'CAF', 'CF', 'Central African Republic', _binary '', 'N', 410, _binary '\0', '2011-01-17 23:13:39',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (38, 'CAN', 'CA', 'Canada', _binary '', 'N', 2, _binary '', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (39, 'CCK', 'CC', 'Cocos (Keeling) Islands', _binary '', 'N', 460, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (40, 'CHE', 'CH', 'Switzerland', _binary '', 'N', 2050, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (41, 'CHL', 'CL', 'Chile', _binary '', 'S', 430, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (42, 'CHN', 'CN', 'China', _binary '', 'N', 440, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (43, 'CIV', 'CI', 'Cote Divoire', _binary '', 'N', 530, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (44, 'CMR', 'CM', 'Cameroon', _binary '', 'N', 370, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (45, 'COD', 'CD', 'Congo, Democratic Republic Of', _binary '', 'N', 490, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (46, 'COG', 'CG', 'Congo, Peoples Republic Of', _binary '', 'N', 500, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (47, 'COK', 'CK', 'Cook Islands', _binary '', 'S', 510, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (48, 'COL', 'CO', 'Colombia', _binary '', 'N', 470, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (49, 'COM', 'KM', 'Comoros', _binary '', 'S', 480, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (50, 'CPV', 'CV', 'Cape Verde', _binary '', 'N', 390, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (51, 'CRI', 'CR', 'Costa Rica', _binary '', 'N', 520, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (52, 'CUB', 'CU', 'Cuba', _binary '', 'N', 550, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (53, 'CXR', 'CX', 'Christmas Island', _binary '', 'N', 450, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (54, 'CYM', 'KY', 'Cayman Islands', _binary '', 'N', 400, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (55, 'CYP', 'CY', 'Cyprus', _binary '', 'N', 560, _binary '\0', '2011-01-17 23:13:40', '2019-08-15 10:34:33');
INSERT INTO `country`
VALUES (56, 'CZE', 'CZ', 'Czech Republic', _binary '', 'N', 570, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (57, 'DEU', 'DE', 'Germany', _binary '', 'N', 820, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (58, 'DJI', 'DJ', 'Djibouti', _binary '', 'N', 590, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (59, 'DMA', 'DM', 'Dominica', _binary '', 'N', 600, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (60, 'DNK', 'DK', 'Denmark', _binary '', 'N', 580, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (61, 'DOM', 'DO', 'Dominican Republic', _binary '', 'N', 610, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (62, 'DZA', 'DZ', 'Algeria', _binary '', 'N', 30, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (63, 'ECU', 'EC', 'Ecuador', _binary '', 'S', 630, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (64, 'EGY', 'EG', 'Egypt', _binary '', 'N', 640, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (65, 'ERI', 'ER', 'Eritrea', _binary '', 'N', 670, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (66, 'ESH', 'EH', 'Western Sahara', _binary '', 'N', 2360, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (67, 'ESP', 'ES', 'Spain', _binary '', 'N', 1960, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (68, 'EST', 'EE', 'Estonia', _binary '', 'N', 680, _binary '\0', '2011-01-17 23:13:40', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (69, 'ETH', 'ET', 'Ethiopia', _binary '', 'N', 690, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (70, 'FIN', 'FI', 'Finland', _binary '', 'N', 730, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (71, 'FJI', 'FJ', 'Fiji', _binary '', 'S', 720, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (72, 'FLK', 'FK', 'Falkland Islands (Malvinas)', _binary '', 'N', 700, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (73, 'FRA', 'FR', 'France', _binary '', 'N', 740, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (74, 'FRO', 'FO', 'Faroe Islands', _binary '', 'N', 710, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (75, 'FSM', 'FM', 'Micronesia, Federated States Of', _binary '', 'N', 1390, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (76, 'FXX', 'FX', 'France, Metropolitan', _binary '', 'N', 750, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (77, 'GAB', 'GA', 'Gabon', _binary '', 'S', 790, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (78, 'GBR', 'GB', 'United Kingdom', _binary '', 'N', 2230, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (79, 'GEO', 'GE', 'Georgia', _binary '', 'N', 810, _binary '\0', '2011-01-17 23:13:40', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (80, 'GHA', 'GH', 'Ghana', _binary '', 'N', 830, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (81, 'GIB', 'GI', 'Gibraltar', _binary '', 'N', 840, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (82, 'GIN', 'GN', 'Guinea', _binary '', 'N', 910, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (83, 'GLP', 'GP', 'Guadeloupe', _binary '', 'N', 880, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (84, 'GMB', 'GM', 'Gambia', _binary '', 'N', 800, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (85, 'GNB', 'GW', 'Guinea-Bissau', _binary '', 'N', 920, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (86, 'GNQ', 'GQ', 'Equatorial Guinea', _binary '', 'N', 660, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (87, 'GRC', 'GR', 'Greece', _binary '', 'N', 850, _binary '\0', '2011-01-17 23:13:40', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (88, 'GRD', 'GD', 'Grenada', _binary '', 'N', 870, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (89, 'GRL', 'GL', 'Greenland', _binary '', 'N', 860, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (90, 'GTM', 'GT', 'Guatemala', _binary '', 'N', 900, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (91, 'GUF', 'GF', 'French Guiana', _binary '', 'N', 760, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (92, 'GUM', 'GU', 'Guam', _binary '', 'N', 890, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (93, 'GUY', 'GY', 'Guyana', _binary '', 'N', 930, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (94, 'HKG', 'HK', 'Hong Kong', _binary '', 'N', 970, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (95, 'HMD', 'HM', 'Heard And Mc Donald Islands', _binary '', 'N', 950, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (96, 'HND', 'HN', 'Honduras', _binary '', 'N', 960, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (97, 'HRV', 'HR', 'Croatia', _binary '', 'N', 540, _binary '\0', '2011-01-17 23:13:40', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (98, 'HTI', 'HT', 'Haiti', _binary '', 'N', 940, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (99, 'HUN', 'HU', 'Hungary', _binary '', 'N', 980, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (100, 'IDN', 'ID', 'Indonesia', _binary '', 'S', 1010, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (101, 'IND', 'IN', 'India', _binary '', 'N', 1000, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (102, 'IOT', 'IO', 'British Indian Ocean Territory', _binary '', 'N', 310, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (103, 'IRL', 'IE', 'Ireland', _binary '', 'N', 1040, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (104, 'IRN', 'IR', 'Iran', _binary '', 'N', 1020, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (105, 'IRQ', 'IQ', 'Iraq', _binary '', 'N', 1030, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (106, 'ISL', 'IS', 'Iceland', _binary '', 'N', 990, _binary '\0', '2011-01-17 23:13:40', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (107, 'ISR', 'IL', 'Israel', _binary '', 'N', 1050, _binary '\0', '2011-01-17 23:13:40', '2019-08-15 10:34:48');
INSERT INTO `country`
VALUES (108, 'ITA', 'IT', 'Italy', _binary '', 'N', 1060, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (109, 'JAM', 'JM', 'Jamaica', _binary '', 'N', 1070, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (110, 'JOR', 'JO', 'Jordan', _binary '', 'N', 1090, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (111, 'JPN', 'JP', 'Japan', _binary '', 'N', 1080, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (112, 'KAZ', 'KZ', 'Kazakhstan', _binary '', 'N', 1100, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (113, 'KEN', 'KE', 'Kenya', _binary '', 'N', 1110, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (114, 'KGZ', 'KG', 'Kyrgyzstan', _binary '', 'N', 1160, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (115, 'KHM', 'KH', 'Cambodia', _binary '', 'N', 360, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (116, 'KIR', 'KI', 'Kiribati', _binary '', 'S', 1120, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (117, 'KNA', 'KN', 'Saint Kitts And Nevis', _binary '', 'N', 1790, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (118, 'KOR', 'KR', 'Korea, South', _binary '', 'N', 1140, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (119, 'KWT', 'KW', 'Kuwait', _binary '', 'N', 1150, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (120, 'LAO', 'LA', 'Lao Peoples Democratic Republic', _binary '', 'N', 1170, _binary '\0',
        '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (121, 'LBN', 'LB', 'Lebanon', _binary '', 'N', 1190, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (122, 'LBR', 'LR', 'Liberia', _binary '', 'N', 1210, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (123, 'LBY', 'LY', 'Libyan Arab Jamahiriya', _binary '', 'N', 1220, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (124, 'LCA', 'LC', 'Saint Lucia', _binary '', 'N', 1800, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (125, 'LIE', 'LI', 'Liechtenstein', _binary '', 'N', 1230, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (126, 'LKA', 'LK', 'Sri Lanka', _binary '', 'N', 1970, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (127, 'LSO', 'LS', 'Lesotho', _binary '', 'S', 1200, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (128, 'LTU', 'LT', 'Lithuania', _binary '', 'N', 1240, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (129, 'LUX', 'LU', 'Luxembourg', _binary '', 'N', 1250, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (130, 'LVA', 'LV', 'Latvia', _binary '', 'N', 1180, _binary '\0', '2011-01-17 23:13:40', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (131, 'MAC', 'MO', 'Macau', _binary '', 'N', 1260, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (132, 'MAR', 'MA', 'Morocco', _binary '', 'N', 1440, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (133, 'MCO', 'MC', 'Monaco', _binary '', 'N', 1410, _binary '\0', '2011-01-17 23:13:40', '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (134, 'MDA', 'MD', 'Moldova, Republic Of', _binary '', 'N', 1400, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (135, 'MDG', 'MG', 'Madagascar', _binary '', 'N', 1280, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (136, 'MDV', 'MV', 'Maldives', _binary '', 'N', 1300, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (137, 'MEX', 'MX', 'Mexico', _binary '', 'N', 1380, _binary '\0', '2011-01-17 23:13:40', '2020-01-08 07:03:11');
INSERT INTO `country`
VALUES (138, 'MHL', 'MH', 'Marshall Islands', _binary '', 'N', 1330, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (139, 'MKD', 'MK', 'Macedonia, The Former Yugoslav Republic Of', _binary '', 'N', 1270, _binary '\0',
        '2011-01-17 23:13:40', '2019-08-15 10:33:00');
INSERT INTO `country`
VALUES (140, 'MLI', 'ML', 'Mali', _binary '', 'N', 1310, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (141, 'MLT', 'MT', 'Malta', _binary '', 'N', 1320, _binary '\0', '2011-01-17 23:13:40', '2019-08-15 10:34:08');
INSERT INTO `country`
VALUES (142, 'MMR', 'MM', 'Myanmar', _binary '', 'N', 1460, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (143, 'MNG', 'MN', 'Mongolia', _binary '', 'N', 1420, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (144, 'MNP', 'MP', 'Northern Mariana Islands', _binary '', 'N', 1590, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (145, 'MOZ', 'MZ', 'Mozambique', _binary '', 'S', 1450, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (146, 'MRT', 'MR', 'Mauritania', _binary '', 'N', 1350, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (147, 'MSR', 'MS', 'Montserrat', _binary '', 'N', 1430, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (148, 'MTQ', 'MQ', 'Martinique', _binary '', 'N', 1340, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (149, 'MUS', 'MU', 'Mauritius', _binary '', 'N', 1360, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (150, 'MYS', 'MY', 'Malaysia', _binary '', 'N', 1290, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (151, 'MYT', 'YT', 'Mayotte', _binary '', 'S', 1370, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (152, 'NAM', 'NA', 'Namibia', _binary '', 'S', 1470, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (153, 'NCL', 'NC', 'New Caledonia', _binary '', 'S', 1520, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (154, 'NER', 'NE', 'Niger', _binary '', 'N', 1550, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (155, 'NFK', 'NF', 'Norfolk Island', _binary '', 'N', 1580, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (156, 'NGA', 'NG', 'Nigeria', _binary '', 'N', 1560, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (157, 'NIC', 'NI', 'Nicaragua', _binary '', 'N', 1540, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (158, 'NIU', 'NU', 'Niue', _binary '', 'S', 1570, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (159, 'NLD', 'NL', 'Netherlands', _binary '', 'N', 1500, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (160, 'NOR', 'NO', 'Norway', _binary '', 'N', 1600, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (161, 'NPL', 'NP', 'Nepal', _binary '', 'N', 1490, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (162, 'NRU', 'NR', 'Nauru', _binary '', 'S', 1480, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (163, 'NZL', 'NZ', 'New Zealand', _binary '', 'S', 1530, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (164, 'OMN', 'OM', 'Oman', _binary '', 'N', 1610, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (165, 'PAK', 'PK', 'Pakistan', _binary '', 'N', 1620, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (166, 'PAN', 'PA', 'Panama', _binary '', 'N', 1650, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (167, 'PCN', 'PN', 'Pitcairn', _binary '', 'N', 1700, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (168, 'PER', 'PE', 'Peru', _binary '', 'S', 1680, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (169, 'PHL', 'PH', 'Philippines', _binary '', 'N', 1690, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (170, 'PLW', 'PW', 'Palau', _binary '', 'N', 1630, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (171, 'PNG', 'PG', 'Papua New Guinea', _binary '', 'S', 1660, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (172, 'POL', 'PL', 'Poland', _binary '', 'N', 1710, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (173, 'PRI', 'PR', 'Puerto Rico', _binary '', 'N', 1730, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (174, 'PRK', 'KP', 'Korea, North', _binary '', 'N', 1130, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (175, 'PRT', 'PT', 'Portugal', _binary '', 'N', 1720, _binary '\0', '2011-01-17 23:13:40',
        '2019-08-15 10:34:21');
INSERT INTO `country`
VALUES (176, 'PRY', 'PY', 'Paraguay', _binary '', 'S', 1670, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (177, 'PSE', 'PS', 'Palestinian Territory, Occupied', _binary '', 'N', 1640, _binary '\0',
        '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (178, 'PYF', 'PF', 'French Polynesia', _binary '', 'S', 770, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (179, 'QAT', 'QA', 'Qatar', _binary '', 'N', 1740, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (180, 'REU', 'RE', 'Reunion', _binary '', 'N', 1750, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (181, 'ROU', 'RO', 'Romania', _binary '', 'N', 1760, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (182, 'RUS', 'RU', 'Russian Federation', _binary '', 'N', 1770, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (183, 'RWA', 'RW', 'Rwanda', _binary '', 'S', 1780, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (184, 'SAU', 'SA', 'Saudi Arabia', _binary '', 'N', 1850, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (185, 'SDN', 'SD', 'Sudan', _binary '', 'N', 2000, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (186, 'SEN', 'SN', 'Senegal', _binary '', 'N', 1860, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (187, 'SGP', 'SG', 'Singapore', _binary '', 'N', 1890, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (188, 'SGS', 'SG', 'South Georgia & South Sandwich Islands Gs', _binary '', 'N', 1950, _binary '\0',
        '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (189, 'SHN', 'SH', 'St. Helena', _binary '', 'N', 1980, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (190, 'SJM', 'SJ', 'Svalbard And Jan Mayen Islands', _binary '', 'N', 2020, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (191, 'SLB', 'SB', 'Solomon Islands', _binary '', 'S', 1920, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (192, 'SLE', 'SL', 'Sierra Leone', _binary '', 'N', 1880, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (193, 'SLV', 'SV', 'El Salvador', _binary '', 'N', 650, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (194, 'SMR', 'SM', 'San Marino', _binary '', 'N', 1830, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (195, 'SOM', 'SO', 'Somalia', _binary '', 'N', 1930, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (196, 'SPM', 'PM', 'St. Pierre And Miquelon', _binary '', 'N', 1990, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (197, 'STP', 'ST', 'Sao Tome And Principe', _binary '', 'N', 1840, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (198, 'SUR', 'SR', 'Suriname', _binary '', 'N', 2010, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (199, 'SVK', 'SK', 'Slovakia', _binary '', 'N', 1900, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (200, 'SVN', 'SI', 'Slovenia', _binary '', 'N', 1910, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 19:08:50');
INSERT INTO `country`
VALUES (201, 'SWE', 'SE', 'Sweden', _binary '', 'N', 2040, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (202, 'SWZ', 'SZ', 'Swaziland', _binary '', 'S', 2030, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (203, 'SYC', 'SC', 'Seychelles', _binary '', 'N', 1870, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (204, 'SYR', 'SY', 'Syrian Arab Republic', _binary '', 'N', 2060, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (205, 'TCA', 'TC', 'Turks & Caicos Islands', _binary '', 'N', 2180, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (206, 'TCD', 'TD', 'Chad', _binary '', 'N', 420, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (207, 'TGO', 'TG', 'Togo', _binary '', 'N', 2110, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (208, 'THA', 'TH', 'Thailand', _binary '', 'N', 2100, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (209, 'TJK', 'TJ', 'Tajikistan', _binary '', 'N', 2080, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (210, 'TKL', 'TK', 'Tokelau', _binary '', 'S', 2120, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (211, 'TKM', 'TM', 'Turkmenistan', _binary '', 'N', 2170, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (212, 'TLS', 'TL', 'East Timor', _binary '', 'S', 620, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (213, 'TON', 'TO', 'Tonga', _binary '', 'S', 2130, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (214, 'TTO', 'TT', 'Trinidad And Tobago', _binary '', 'N', 2140, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (215, 'TUN', 'TN', 'Tunisia', _binary '', 'N', 2150, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (216, 'TUR', 'TR', 'Turkey', _binary '', 'N', 2160, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (217, 'TUV', 'TV', 'Tuvalu', _binary '', 'S', 2190, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (218, 'TWN', 'TW', 'Taiwan', _binary '', 'N', 2070, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (219, 'TZA', 'TZ', 'Tanzania, United Republic Of', _binary '', 'N', 2090, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (220, 'UGA', 'UG', 'Uganda', _binary '', 'N', 2200, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (221, 'UKR', 'UA', 'Ukraine', _binary '', 'N', 2210, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (222, 'UMI', 'UM', 'United States, Minor Islands', _binary '', 'N', 2250, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (223, 'UNK', 'UN', 'Unknown', _binary '\0', 'N', 2260, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (224, 'URY', 'UY', 'Uruguay', _binary '', 'S', 2270, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (225, 'USA', 'US', 'United States', _binary '', 'N', 1, _binary '', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (226, 'UZB', 'UZ', 'Uzbekistan', _binary '', 'N', 2280, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (227, 'VAT', 'VA', 'Vatican City StateRepository', _binary '', 'N', 2300, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (228, 'VCT', 'VC', 'Saint Vincent And The Grenadines', _binary '', 'N', 1810, _binary '\0',
        '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (229, 'VEN', 'VE', 'Venezuela', _binary '', 'N', 2310, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (230, 'VGB', 'VG', 'Virgin Islands, British', _binary '', 'N', 2330, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (231, 'VIR', 'VI', 'Virgin Islands, U.S.', _binary '', 'N', 2340, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (232, 'VNM', 'VN', 'Vietnam', _binary '', 'N', 2320, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (233, 'VUT', 'VU', 'Vanuatu', _binary '', 'S', 2290, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (234, 'WLF', 'WF', 'Wallis & Futuna Islands', _binary '', 'N', 2350, _binary '\0', '2011-01-17 23:13:40',
        '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (235, 'WSM', 'WS', 'Samoa', _binary '', 'S', 1820, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (236, 'YEM', 'YE', 'Yemen', _binary '', 'N', 2370, _binary '\0', '2011-01-17 23:13:40', '2014-07-23 11:36:43');
INSERT INTO `country`
VALUES (237, 'YUG', 'YU', 'Yugoslavia', _binary '', 'N', 2380, _binary '\0', '2011-01-17 23:13:40',
        '2019-02-01 20:05:18');
INSERT INTO `country`
VALUES (238, 'ZAF', 'ZA', 'South Africa', _binary '', 'S', 1940, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (239, 'ZMB', 'ZM', 'Zambia', _binary '', 'S', 2390, _binary '\0', '2011-01-17 23:13:40', '2014-08-07 17:54:10');
INSERT INTO `country`
VALUES (240, 'ZWE', 'ZW', 'Zimbabwe', _binary '', 'S', 2400, _binary '\0', '2011-01-17 23:13:40',
        '2014-08-07 17:54:10');


INSERT INTO `state`
VALUES (1, 73, '01', 'Ain', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (2, 73, '02', 'Aisne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (3, 73, '03', 'Allier', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (4, 73, '04', 'Alpes-de-Haute-Provence', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (5, 73, '05', 'Hautes-Alpes', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (6, 73, '06', 'Alpes-Maritimes', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (7, 73, '07', 'Ardèche', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (8, 73, '08', 'Ardennes', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (9, 73, '09', 'Ariège', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (10, 16, 'BU', 'Burgenland', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (11, 73, '10', 'Aube', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (12, 73, '11', 'Aude', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (13, 73, '12', 'Aveyron', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (14, 73, '13', 'Bouches-du-Rhône', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (15, 73, '14', 'Calvados', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (16, 73, '15', 'Cantal', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (17, 73, '16', 'Charente', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (18, 73, '17', 'Charente-Maritime', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (19, 73, '18', 'Cher', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (20, 73, '19', 'Corrèze', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (21, 16, 'KA', 'Karnten', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (22, 73, '21', 'Côte-d\'Or', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (23, 73, '22', 'Côtes-d\'Armor', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (24, 73, '23', 'Creuse', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (25, 73, '24', 'Dordogne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (26, 73, '25', 'Doubs', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (27, 73, '26', 'Drôme', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (28, 73, '27', 'Eure', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (29, 73, '28', 'Eure-et-Loir', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:15');
INSERT INTO `state`
VALUES (30, 73, '29', 'Finistère', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (31, 73, '2A', 'Corse-du-Sud', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (32, 73, '2B', 'Haute-Corse', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (33, 16, 'NO', 'Niederosterreich', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (34, 73, '30', 'Gard', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (35, 73, '31', 'Haute-Garonne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (36, 73, '32', 'Gers', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (37, 73, '33', 'Gironde', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (38, 73, '34', 'Hérault', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (39, 73, '35', 'Ille-et-Vilaine', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (40, 73, '36', 'Indre', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (41, 73, '37', 'Indre-et-Loire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (42, 73, '38', 'Isère', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (43, 73, '39', 'Jura', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (44, 16, 'OO', 'Oberosterreich', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (45, 73, '40', 'Landes', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (46, 73, '41', 'Loir-et-Cher', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (47, 73, '42', 'Loire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (48, 73, '43', 'Haute-Loire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (49, 73, '44', 'Loire-Atlantique', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (50, 73, '45', 'Loiret', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (51, 73, '46', 'Lot', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (52, 73, '47', 'Lot-et-Garonne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (53, 73, '48', 'Lozère', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (54, 73, '49', 'Maine-et-Loire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (55, 16, 'SZ', 'Salzburg', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (56, 73, '50', 'Manche', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (57, 73, '51', 'Marne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (58, 73, '52', 'Haute-Marne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (59, 73, '53', 'Mayenne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (60, 73, '54', 'Meurthe-et-Moselle', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (61, 73, '55', 'Meuse', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (62, 73, '56', 'Morbihan', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (63, 73, '57', 'Moselle', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (64, 73, '58', 'Nièvre', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (65, 73, '59', 'Nord', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (66, 16, 'ST', 'Steiermark', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (67, 73, '60', 'Oise', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (68, 73, '61', 'Orne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (69, 73, '62', 'Pas-de-Calais', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (70, 73, '63', 'Puy-de-Dôme', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (71, 73, '64', 'Pyrénées-Atlantiques', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (72, 73, '65', 'Hautes-Pyrénées', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (73, 73, '66', 'Pyrénées-Orientales', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (74, 73, '67', 'Bas-Rhin', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (75, 73, '68', 'Haut-Rhin', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (76, 73, '69', 'Rhône', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (77, 16, 'TR', 'Tirol', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (78, 73, '70', 'Haute-Saône', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (79, 73, '71', 'Saône-et-Loire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (80, 73, '72', 'Sarthe', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (81, 73, '73', 'Savoie', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (82, 73, '74', 'Haute-Savoie', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (83, 73, '75', 'Paris', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (84, 73, '76', 'Seine-Maritime', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (85, 73, '77', 'Seine-et-Marne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (86, 73, '78', 'Yvelines', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (87, 73, '79', 'Deux-Sèvres', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (88, 16, 'VO', 'Vorarlberg', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (89, 73, '80', 'Somme', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (90, 73, '81', 'Tarn', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (91, 73, '82', 'Tarn-et-Garonne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (92, 73, '83', 'Var', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (93, 73, '84', 'Vaucluse', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (94, 73, '85', 'Vendée', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (95, 73, '86', 'Vienne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (96, 73, '87', 'Haute-Vienne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (97, 73, '88', 'Vosges', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (98, 73, '89', 'Yonne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (99, 16, 'WI', 'Wien', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (100, 73, '90', 'Territoire-de-Belfort', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (101, 73, '91', 'Essonne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (102, 73, '92', 'Hauts-de-Seine', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (103, 73, '93', 'Seine-Saint-Denis', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (104, 73, '94', 'Val-de-Marne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (105, 73, '95', 'Val-d\'Oise', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (106, 9, 'SA', 'Salta', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (107, 67, 'A', 'Alicante', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (108, 38, 'AB', 'Alberta', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (109, 67, 'AB', 'Albacete', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (110, 78, 'ABD', 'Aberdeenshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (111, 78, 'ABE', 'Aberdeen City', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (112, 31, 'AC', 'Acre', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (113, 40, 'AG', 'Aargau', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (114, 108, 'AG', 'Agrigento', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (115, 137, 'AG', 'Aguascalientes', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (116, 78, 'AGB', 'Argyll and Bute', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (117, 78, 'AGY', 'Isle of Anglesey', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (118, 225, 'AK', 'Alaska', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (119, 31, 'AL', 'Alagoas', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (120, 67, 'AL', 'Almería', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (121, 108, 'AL', 'Alessandria', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (122, 225, 'AL', 'Alabama', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (123, 31, 'AM', 'Amazonas', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (124, 19, 'AN', 'Antwerp', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (125, 67, 'AN', 'Andalucía', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (126, 108, 'AN', 'Ancona', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (127, 78, 'ANS', 'Angus', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (128, 78, 'ANT', 'Antrim', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (129, 108, 'AO', 'Aosta', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (130, 31, 'AP', 'Amapa', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (131, 108, 'AP', 'Ascoli Piceno', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (132, 108, 'AQ', 'L\'Aquila', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (133, 40, 'AR', 'appenzell Innerrhoden', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (134, 67, 'AR', 'Aragón', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (135, 108, 'AR', 'Arezzo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (136, 225, 'AR', 'Arkansas', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (137, 78, 'ARD', 'Ards', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (138, 78, 'ARM', 'Armagh', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (139, 223, 'AS', 'American Samoa', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (140, 108, 'AT', 'Asti', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (141, 67, 'AV', 'Ávila', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (142, 108, 'AV', 'Avellino', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (143, 225, 'AZ', 'Arizona', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (144, 9, 'BA', 'Buenos Aires Province', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (145, 67, 'B', 'Barcelona', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (146, 31, 'BA', 'Bahia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (147, 67, 'BA', 'Badajoz', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (148, 108, 'BA', 'Bari', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (149, 78, 'BAS', 'Bath and North East Somerset', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (150, 78, 'BBD', 'Blackburn with Darwen', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (151, 38, 'BC', 'British Columbia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (152, 78, 'BDF', 'Bedfordshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (153, 40, 'BE', 'Bern', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (154, 57, 'BE', 'Berlin', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (155, 78, 'BEN', 'Brent', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (156, 78, 'BEX', 'Bexley', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (157, 78, 'BFS', 'Belfast', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (158, 108, 'BG', 'Bergamo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (159, 78, 'BGE', 'Bridgend', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (160, 78, 'BGW', 'Blaenau Gwent', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (161, 67, 'BI', 'Vizcaya', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (162, 108, 'BI', 'Biella', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (163, 78, 'BIR', 'Birmingham', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (164, 137, 'BJ', 'Baja California', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (165, 78, 'BKM', 'Buckinghamshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (166, 40, 'BL', 'Basel-Landschaft', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (167, 108, 'BL', 'Belluno', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (168, 78, 'BLA', 'Ballymena', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (169, 78, 'BLY', 'Ballymoney', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (170, 78, 'BMH', 'Bournemouth', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (171, 108, 'BN', 'Benevento', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (172, 78, 'BNB', 'Banbridge', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (173, 78, 'BNE', 'Barnet', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (174, 78, 'BNH', 'Brighton and Hove', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (175, 78, 'BNS', 'Barnsley', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (176, 108, 'BO', 'Bologna', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (177, 78, 'BOL', 'Bolton', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (178, 78, 'BPL', 'Blackpool', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (179, 57, 'BR', 'Brandenburg', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (180, 108, 'BR', 'Brindisi', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (181, 78, 'BRC', 'Bracknell Forest', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (182, 78, 'BRD', 'Bradford', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (183, 78, 'BRY', 'Bromley', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (184, 40, 'BS', 'Basel-Stadt', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (185, 108, 'BS', 'Brescia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (186, 137, 'BS', 'Baja California Sur', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (187, 78, 'BST', 'Bristol, City of', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (188, 67, 'BU', 'Burgos', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (189, 78, 'BUR', 'Bury', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (190, 19, 'BW', 'Walloon Brabant', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (191, 57, 'BW', 'Baden-Württemberg', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (192, 57, 'BY', 'Bavaria (Bayern)', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (193, 108, 'BZ', 'Bolzano', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (194, 9, 'CA', 'Distrito Federal', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (195, 67, 'C', 'A Coruña', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (196, 103, 'C', 'Cork', _binary '\0', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (197, 67, 'CA', 'Cádiz', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (198, 108, 'CA', 'Cagliari', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (199, 225, 'CA', 'California', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (200, 78, 'CAM', 'Cambridgeshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (201, 78, 'CAY', 'Caerphilly', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (202, 108, 'CB', 'Campobasso', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (203, 67, 'CC', 'Cáceres', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (204, 31, 'CE', 'Ceara', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (205, 67, 'CE', 'Ceuta', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (206, 103, 'CE', 'Clare', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (207, 108, 'CE', 'Caserta', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (208, 78, 'CGN', 'Ceredigion', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (209, 78, 'CGV', 'Craigavon', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (210, 108, 'CH', 'Chieti', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (211, 137, 'CH', 'Chiapas', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (212, 78, 'CHS', 'Cheshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (213, 137, 'CI', 'Chihuahua', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (214, 78, 'CKF', 'Carrickfergus', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (215, 78, 'CKT', 'Cookstown', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (216, 67, 'CL', 'Castilla y León', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (217, 108, 'CL', 'Caltanissetta', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (218, 137, 'CL', 'Colima', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (219, 78, 'CLD', 'Calderdale', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (220, 78, 'CLK', 'Clackmannanshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (221, 78, 'CLR', 'Coleraine', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (222, 67, 'CM', 'Castilla-La Mancha', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (223, 78, 'CMA', 'Cumbria', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (224, 78, 'CMD', 'Camden', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (225, 78, 'CMN', 'Carmarthenshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (226, 67, 'CN', 'Canarias', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (227, 103, 'CN', 'Cavan', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (228, 108, 'CN', 'Cuneo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (229, 67, 'CO', 'Córdoba', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (230, 108, 'CO', 'Como', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (231, 225, 'CO', 'Colorado', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (232, 78, 'CON', 'Cornwall', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (233, 78, 'COV', 'Coventry', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (234, 137, 'CP', 'Campeche', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (235, 67, 'CR', 'Ciudad Real', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (236, 108, 'CR', 'Cremona', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (237, 78, 'CRF', 'Cardiff', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (238, 78, 'CRY', 'Croydon', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (239, 67, 'CS', 'Castellón', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (240, 108, 'CS', 'Cosenza', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (241, 78, 'CSR', 'Castlereagh', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (242, 15, 'CT', 'Australian Capital Territory', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (243, 67, 'CT', 'Cataluña', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (244, 108, 'CT', 'Catania', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (245, 225, 'CT', 'Connecticut', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (246, 67, 'CU', 'Cuenca', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (247, 137, 'CU', 'Coahuila de Zaragoza', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (248, 103, 'CW', 'Carlow', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (249, 78, 'CWY', 'Conwy', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (250, 108, 'CZ', 'Catanzaro', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (251, 9, 'SL', 'San Luis', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (252, 103, 'D', 'Dublin', _binary '\0', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (253, 78, 'DAL', 'Darlington', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (254, 78, 'DBY', 'Derbyshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (255, 225, 'DC', 'District of Columbia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (256, 225, 'DE', 'Delaware', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (257, 78, 'DEN', 'Denbighshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (258, 78, 'DER', 'Derby', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (259, 78, 'DEV', 'Devon', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (260, 31, 'DF', 'Distrito Federal', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (261, 137, 'DF', 'Distrito Federal', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (262, 137, 'DG', 'Durango', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (263, 78, 'DGN', 'Dungannon', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (264, 78, 'DGY', 'Dumfries and Galloway', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (265, 103, 'DL', 'Donegal', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (266, 78, 'DNC', 'Doncaster', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (267, 78, 'DND', 'Dundee City', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (268, 78, 'DOR', 'Dorset', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (269, 78, 'DOW', 'Down', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (270, 159, 'DR', 'Drenthe', _binary '', NULL, '2018-05-22 21:51:09', '2018-05-22 21:51:09');
INSERT INTO `state`
VALUES (271, 78, 'DRY', 'Derry', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (272, 78, 'DUD', 'Dudley', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (273, 78, 'DUR', 'Durham', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (274, 9, 'ER', 'Entre Rios', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (275, 78, 'EAL', 'Ealing', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (276, 78, 'EAY', 'East Ayrshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (277, 78, 'EDH', 'Edinburgh, City of', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (278, 78, 'EDU', 'East Dunbartonshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (279, 78, 'ELN', 'East Lothian', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (280, 78, 'ELS', 'Eilean Siar', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (281, 137, 'EM', 'Estado Mexico', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (282, 108, 'EN', 'Enna', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (283, 78, 'ENF', 'Enfield', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (284, 78, 'ERW', 'East Renfrewshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (285, 78, 'ERY', 'East Riding of Yorkshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (286, 31, 'ES', 'Espirito Santo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (287, 78, 'ESS', 'Essex', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (288, 78, 'ESX', 'East Sussex', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (289, 67, 'EX', 'Extremadura', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (290, 9, 'LR', 'La Rioja', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (291, 78, 'FAL', 'Falkirk', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (292, 108, 'FC', 'Forli-Cesena', _binary '', NULL, '2012-04-25 20:51:11', '2012-04-25 20:51:11');
INSERT INTO `state`
VALUES (293, 108, 'FE', 'Ferrara', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (294, 159, 'FE', 'Flevoland', _binary '', NULL, '2018-05-22 21:51:45', '2018-05-22 21:51:45');
INSERT INTO `state`
VALUES (295, 78, 'FER', 'Fermanagh', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (296, 108, 'FG', 'Foggia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (297, 108, 'FI', 'Florence', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (298, 78, 'FIF', 'Fife', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (299, 225, 'FL', 'Florida', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (300, 78, 'FLN', 'Flintshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (301, 223, 'FM', 'Federated States of Micronesia', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (302, 108, 'FO', 'Forli', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (303, 40, 'FR', 'Fribourg', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (304, 108, 'FR', 'Frosinone', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (305, 159, 'FR', 'Friesland', _binary '', NULL, '2018-05-22 21:51:55', '2018-05-22 21:51:55');
INSERT INTO `state`
VALUES (306, 9, 'SE', 'Santiago del Estero', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (307, 103, 'G', 'Galway', _binary '\0', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (308, 67, 'GA', 'Galicia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (309, 225, 'GA', 'Georgia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (310, 78, 'GAT', 'Gateshead', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (311, 67, 'GC', 'Las Palmas', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (312, 40, 'GE', 'Geneva', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (313, 108, 'GE', 'Genova', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (314, 159, 'GE', 'Gelderland', _binary '', NULL, '2018-05-22 21:52:04', '2018-05-22 21:52:04');
INSERT INTO `state`
VALUES (315, 67, 'GI', 'Girona', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (316, 137, 'GJ', 'Guanajuato', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (317, 40, 'GL', 'Glarus', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (318, 78, 'GLG', 'Glasgow City', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (319, 78, 'GLS', 'Gloucestershire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (320, 31, 'GO', 'Goias', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (321, 108, 'GO', 'Gorizia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (322, 40, 'GR', 'Graubünden', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (323, 67, 'GR', 'Granada', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (324, 108, 'GR', 'Grosseto', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (325, 137, 'GR', 'Guerrero', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (326, 159, 'GR', 'Groningen', _binary '', NULL, '2018-05-22 21:52:14', '2018-05-22 21:52:14');
INSERT INTO `state`
VALUES (327, 78, 'GRE', 'Greenwich', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (328, 67, 'GU', 'Guadalajara', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (329, 223, 'GU', 'Guam', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (330, 78, 'GWN', 'Gwynedd', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (331, 9, 'CC', 'Chaco', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (332, 67, 'H', 'Huelva', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (333, 78, 'HAL', 'Halton', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (334, 78, 'HAM', 'Hampshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (335, 78, 'HAV', 'Havering', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (336, 57, 'HB', 'Bremen', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (337, 78, 'HCK', 'Hackney', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (338, 57, 'HE', 'Hes (Hesn)', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (339, 78, 'HEF', 'Herefordshire, County of', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (340, 137, 'HG', 'Hidalgo', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (341, 57, 'HH', 'Hamburg', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (342, 225, 'HI', 'Hawaii', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (343, 78, 'HIL', 'Hillingdon', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (344, 78, 'HLD', 'Highland', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (345, 78, 'HMF', 'Hammersmith and Fulham', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (346, 78, 'HNS', 'Hounslow', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (347, 78, 'HPL', 'Hartlepool', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (348, 78, 'HRT', 'Hertfordshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (349, 78, 'HRW', 'Harrow', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (350, 78, 'HRY', 'Haringey', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (351, 19, 'HT', 'Hainaut', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (352, 67, 'HU', 'Huesca', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (353, 225, 'IA', 'Iowa', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (354, 67, 'IB', 'Illes Balears', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (355, 225, 'ID', 'Idaho', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (356, 225, 'IL', 'Illinois', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (357, 108, 'IM', 'Imperia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (358, 225, 'IN', 'Indiana', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (359, 78, 'IOS', 'Isles of Scilly', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (360, 78, 'IOW', 'Isle of Wight', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (361, 108, 'IS', 'Isernia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (362, 78, 'ISL', 'Islington', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (363, 78, 'IVC', 'Inverclyde', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (364, 9, 'SJ', 'San Juan', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (365, 67, 'J', 'Jaén', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (366, 137, 'JA', 'Jalisco', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (367, 56, 'JC', 'South Bohemian Region (Jihoceský kraj)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (368, 56, 'JM', 'South Moravian Region (Jihomoravský kraj)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (369, 40, 'JU', 'Jura', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (370, 9, 'CT', 'Catamarca', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (371, 56, 'KA', 'Karlovy Vary Region (Karlovarský kraj)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (372, 103, 'KE', 'Kildare', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (373, 78, 'KEC', 'Kensington and Chelsea', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (374, 78, 'KEN', 'Kent', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (375, 78, 'KHL', 'Kingston upon Hull, City of', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (376, 78, 'KIR', 'Kirklees', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (377, 103, 'KK', 'Kilkenny', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (378, 56, 'KR', 'Hradec Kralove Region (Královéhradecký kraj)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (379, 108, 'KR', 'Crotone', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (380, 225, 'KS', 'Kansas', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (381, 78, 'KTT', 'Kingston upon Thames', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (382, 78, 'KWL', 'Knowsley', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (383, 103, 'KY', 'Kerry', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (384, 225, 'KY', 'Kentucky', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (385, 9, 'LP', 'La Pampa', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (386, 67, 'L', 'Lleida', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (387, 225, 'LA', 'Louisiana', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (388, 78, 'LAN', 'Lancashire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (389, 78, 'LBH', 'Lambeth', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (390, 108, 'LC', 'Lecco', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (391, 78, 'LCE', 'Leicester', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (392, 103, 'LD', 'Longford', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (393, 78, 'LDS', 'Leeds', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (394, 67, 'LE', 'León', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (395, 108, 'LE', 'Lecce', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (396, 78, 'LEC', 'Leicestershire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (397, 78, 'LEW', 'Lewisham', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (398, 19, 'LG', 'Liege', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (399, 103, 'LH', 'Louth', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (400, 19, 'LI', 'Limburg', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (401, 56, 'LI', 'Liberec Region (Liberecký kraj)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (402, 108, 'LI', 'Livorno', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (403, 159, 'LI', 'Limburg', _binary '', NULL, '2018-05-22 21:52:27', '2018-05-22 21:52:27');
INSERT INTO `state`
VALUES (404, 78, 'LIN', 'Lincolnshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (405, 78, 'LIV', 'Liverpool', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (406, 103, 'LK', 'Limerick', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (407, 103, 'LM', 'Leitrim', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (408, 78, 'LMV', 'Limavady', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (409, 78, 'LND', 'London, City of', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (410, 67, 'LO', 'La Rioja', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (411, 108, 'LO', 'Lodi', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (412, 78, 'LRN', 'Larne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (413, 103, 'LS', 'Laois', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (414, 78, 'LSB', 'Lisburn', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (415, 108, 'LT', 'Latina', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (416, 40, 'LU', 'Lucerne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (417, 67, 'LU', 'Lugo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (418, 108, 'LU', 'Lucca', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (419, 78, 'LUT', 'Luton', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (420, 19, 'LX', 'Luxembourg', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (421, 9, 'MZ', 'Mendoza', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (422, 67, 'M', 'Madrid', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (423, 31, 'MA', 'Maranhao', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (424, 67, 'MA', 'Málaga', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (425, 225, 'MA', 'Massachusetts', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (426, 78, 'MAN', 'Manchester', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (427, 38, 'MB', 'Manitoba', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (428, 108, 'MB', 'Monza-Brianza', _binary '', NULL, '2012-04-25 20:51:11', '2012-04-25 20:51:11');
INSERT INTO `state`
VALUES (429, 108, 'MC', 'Macerata', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (430, 225, 'MD', 'Maryland', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (431, 78, 'MDB', 'Middlesbrough', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (432, 78, 'MDW', 'Medway', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (433, 108, 'ME', 'Messina', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (434, 225, 'ME', 'Maine', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (435, 78, 'MFT', 'Magherafelt', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (436, 31, 'MG', 'Minas Gerais', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (437, 103, 'MH', 'Meath', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (438, 137, 'MH', 'Michoacan', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (439, 223, 'MH', 'Marshall Islands', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (440, 108, 'MI', 'Milan', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (441, 225, 'MI', 'Michigan', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (442, 78, 'MIK', 'Milton Keynes', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (443, 67, 'ML', 'Melilla', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (444, 78, 'MLN', 'Midlothian', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (445, 103, 'MN', 'Monaghan', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (446, 108, 'MN', 'Mantua', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (447, 225, 'MN', 'Minnesota', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (448, 56, 'MO', 'Moravian-Silesian Region (Moravskoslezský kraj)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (449, 103, 'MO', 'Mayo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (450, 108, 'MO', 'Modena', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (451, 225, 'MO', 'Missouri', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (452, 78, 'MON', 'Monmouthshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (453, 223, 'MP', 'Northern Mariana Islands', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (454, 137, 'MR', 'Morelos', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (455, 78, 'MRT', 'Merton', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (456, 78, 'MRY', 'Moray', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (457, 31, 'MS', 'Mato Grosso do Sul', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (458, 108, 'MS', 'Massa-Carrara', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (459, 225, 'MS', 'Mississippi', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (460, 31, 'MT', 'Mato Grosso', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (461, 108, 'MT', 'Matera', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (462, 225, 'MT', 'Montana', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (463, 78, 'MTY', 'Merthyr Tydfil', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (464, 67, 'MU', 'Murcia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (465, 57, 'MV', 'Mecklenburg-Western Pomerania (Vorpommern)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (466, 137, 'MX', 'Mexico', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (467, 78, 'MYL', 'Moyle', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (468, 9, 'MN', 'Misiones', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (469, 19, 'NA', 'Namur', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (470, 67, 'NA', 'Navarra', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (471, 108, 'NA', 'Napoli', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (472, 137, 'NA', 'Nayarit', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (473, 78, 'NAY', 'North Ayrshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (474, 38, 'NB', 'New Brunswick', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (475, 159, 'NB', 'Noord Brabant', _binary '', NULL, '2018-05-22 21:52:39', '2018-05-22 21:52:39');
INSERT INTO `state`
VALUES (476, 78, 'NBL', 'Northumberland', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (477, 73, 'NC', 'New Caledonia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (478, 225, 'NC', 'North Carolina', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (479, 225, 'ND', 'North Dakota', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (480, 78, 'NDN', 'North Down NIR', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (481, 40, 'NE', 'Neuch?tel', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (482, 225, 'NE', 'Nebraska', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (483, 78, 'NEL', 'North East Lincolnshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (484, 78, 'NET', 'Newcastle upon Tyne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (485, 78, 'NFK', 'Norfolk', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (486, 78, 'NGM', 'Nottingham', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (487, 159, 'NH', 'Noord Holland', _binary '', NULL, '2018-05-22 21:52:57', '2018-05-22 21:52:57');
INSERT INTO `state`
VALUES (488, 225, 'NH', 'New Hampshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (489, 57, 'NI', 'Lower Saxony (Niedersachn)', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (490, 225, 'NJ', 'New Jersey', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (491, 38, 'NL', 'Newfoundland and Labrador', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (492, 137, 'NL', 'Nuevo Leon', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (493, 78, 'NLK', 'North Lanarkshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (494, 78, 'NLN', 'North Lincolnshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (495, 225, 'NM', 'New Mexico', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (496, 108, 'NO', 'Novara', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (497, 15, 'NS', 'New South Wales', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (498, 38, 'NS', 'Nova Scotia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (499, 78, 'NSM', 'North Somerset', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (500, 15, 'NT', 'Northern Territory', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (501, 78, 'NTA', 'Newtownabbey', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (502, 78, 'NTH', 'Northamptonshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (503, 78, 'NTL', 'Neath Port Talbot', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (504, 78, 'NTT', 'Nottinghamshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (505, 78, 'NTY', 'North Tyneside', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (506, 38, 'NU', 'Nunavut', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (507, 108, 'NU', 'Nuoro', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (508, 225, 'NV', 'Nevada', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (509, 40, 'NW', 'Nidwalden', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (510, 57, 'NW', 'North Rhine-Westphalia (Nordrhein-Westfalen)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (511, 78, 'NWM', 'Newham', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (512, 78, 'NWP', 'Newport', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (513, 225, 'NY', 'New York', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (514, 78, 'NYK', 'North Yorkshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (515, 78, 'NYM', 'Newry and Mourne', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (516, 67, 'O', 'Asturias', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (517, 137, 'OA', 'Oaxaca', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (518, 225, 'OH', 'Ohio', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (519, 225, 'OK', 'Oklahoma', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (520, 56, 'OL', 'Olomouc Region (Olomoucký kraj)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (521, 78, 'OLD', 'Oldham', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (522, 78, 'OMH', 'Omagh', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (523, 38, 'ON', 'Ontario', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (524, 67, 'OR', 'Ourense', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (525, 108, 'OR', 'Oristano', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (526, 225, 'OR', 'Oregon', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (527, 78, 'ORK', 'Orkney Islands', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (528, 19, 'OV', 'Oost-Vlaanderen', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (529, 159, 'OV', 'Overijssel', _binary '', NULL, '2018-05-22 21:53:15', '2018-05-22 21:53:15');
INSERT INTO `state`
VALUES (530, 40, 'OW', 'Obwalden', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (531, 78, 'OXF', 'Oxfordshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (532, 103, 'OY', 'Offaly', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (533, 9, 'FM', 'Formosa', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (534, 67, 'P', 'Palencia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (535, 31, 'PA', 'Para', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (536, 56, 'PA', 'Pardubice Region (Pardubický kraj)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (537, 108, 'PA', 'Palermo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (538, 225, 'PA', 'Pennsylvania', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (539, 31, 'PB', 'Paraiba', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (540, 108, 'PC', 'Piacenza', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (541, 108, 'PD', 'Padua', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (542, 31, 'PE', 'Pernambuco', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (543, 38, 'PE', 'Prince Edward Island', _binary '', NULL, '2015-05-18 15:02:11', '2015-05-18 15:02:11');
INSERT INTO `state`
VALUES (544, 108, 'PE', 'Pescara', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (545, 78, 'PEM', 'Pembrokeshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (546, 73, 'PF', 'French Polynesia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (547, 108, 'PG', 'Perugia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (548, 31, 'PI', 'Piaui', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (549, 108, 'PI', 'Pisa', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (550, 78, 'PKN', 'Perth and Kinross', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (551, 56, 'PL', 'Plzen Region (Plzenský kraj)', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (552, 78, 'PLY', 'Plymouth, England', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (553, 67, 'PM', 'Baleares', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (554, 73, 'PM', 'Saint-Pierre and Miquelon', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (555, 108, 'PN', 'Pordenone', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (556, 67, 'PO', 'Pontevedra', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (557, 108, 'PO', 'Prato', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (558, 78, 'POL', 'Poole', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (559, 78, 'POR', 'Portsmouth', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (560, 78, 'POW', 'Powys', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (561, 31, 'PR', 'Parana', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (562, 56, 'PR', 'Prague (Praha, hlavní mesto)', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (563, 108, 'PR', 'Parma', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (564, 225, 'PR', 'Puerto Rico', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (565, 108, 'PT', 'Pistoia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (566, 78, 'PTE', 'Peterborough', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (567, 108, 'PU', 'Pesaro e Urbino', _binary '', NULL, '2011-01-17 23:19:43', '2012-04-09 22:27:34');
INSERT INTO `state`
VALUES (568, 137, 'PU', 'Puebla', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (569, 67, 'PV', 'País Vasco', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (570, 108, 'PV', 'Pavia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (571, 223, 'PW', 'Palau', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (572, 108, 'PZ', 'Potenza', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (573, 9, 'NQ', 'Neuquen', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (574, 137, 'QA', 'Queretaro', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (575, 38, 'QC', 'Quebec', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (576, 15, 'QL', 'Queensland', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (577, 137, 'QR', 'Quintana Roo', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (578, 9, 'RN', 'Rio Negro', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (579, 108, 'RA', 'Ravenna', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (580, 108, 'RC', 'Reggio Calabria', _binary '', NULL, '2011-01-17 23:19:43', '2016-10-20 13:06:14');
INSERT INTO `state`
VALUES (581, 78, 'RCC', 'Redcar and Cleveland', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (582, 78, 'RCH', 'Rochdale', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (583, 78, 'RCT', 'Rhondda Cynon Taf', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (584, 78, 'RDB', 'Redbridge', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (585, 78, 'RDG', 'Reading', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (586, 108, 'RE', 'Reggio Emilia', _binary '', NULL, '2011-01-17 23:19:43', '2016-10-20 13:05:42');
INSERT INTO `state`
VALUES (587, 78, 'RFW', 'Renfrewshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (588, 108, 'RG', 'Ragusa', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (589, 108, 'RI', 'Rieti', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (590, 225, 'RI', 'Rhode Island', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (591, 78, 'RIC', 'Richmond upon Thames', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (592, 31, 'RJ', 'Rio de Janeiro', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (593, 108, 'RM', 'Rome Roma', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (594, 31, 'RN', 'Rio Grande do Norte', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (595, 103, 'RN', 'Roscommon', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (596, 108, 'RN', 'Rimini', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (597, 31, 'RO', 'Rondonia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (598, 108, 'RO', 'Rovigo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (599, 78, 'ROT', 'Rotherham', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (600, 57, 'RP', 'Rhineland-Palatinate (Pfalz)', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (601, 31, 'RR', 'Roraima', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (602, 31, 'RS', 'Rio Grande do Sul', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (603, 78, 'RUT', 'Rutland', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (604, 9, 'SF', 'Santa Fe', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (605, 67, 'S', 'Cantabria', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (606, 15, 'SA', 'South Australia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (607, 67, 'SA', 'Salamanca', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (608, 108, 'SA', 'Salerno', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (609, 78, 'SAW', 'Sandwell', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (610, 78, 'SAY', 'South Ayrshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (611, 31, 'SC', 'Santa Catarina', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (612, 225, 'SC', 'South Carolina', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (613, 78, 'SCB', 'Scottish Borders, The', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (614, 225, 'SD', 'South Dakota', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (615, 31, 'SE', 'Sergipe', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (616, 67, 'SE', 'Sevilla', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (617, 78, 'SFK', 'Suffolk', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (618, 78, 'SFT', 'Sefton', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (619, 40, 'SG', 'St. Gallen', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (620, 67, 'SG', 'Segovia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (621, 78, 'SGC', 'South Gloucestershire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (622, 40, 'SH', 'Schaffhausen', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (623, 57, 'SH', 'Schleswig-Holstein', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (624, 78, 'SHF', 'Sheffield', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (625, 78, 'SHN', 'St Helens', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (626, 78, 'SHR', 'Shropshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (627, 108, 'SI', 'Siena', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (628, 137, 'SI', 'Sinaloa', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (629, 38, 'SK', 'Saskatchewan', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (630, 78, 'SKP', 'Stockport', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (631, 57, 'SL', 'Saarland', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (632, 137, 'SL', 'San Luis Potosi', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (633, 78, 'SLF', 'Salford', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (634, 78, 'SLG', 'Slough', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (635, 78, 'SLK', 'South Lanarkshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (636, 57, 'SN', 'Saxony (Sachn)', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (637, 78, 'SND', 'Sunderland', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (638, 40, 'SO', 'Solothurn', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (639, 67, 'SO', 'Soria', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (640, 103, 'SO', 'Sligo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (641, 108, 'SO', 'Sondrio', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (642, 137, 'SO', 'Sonora', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (643, 78, 'SOL', 'Solihull', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (644, 78, 'SOM', 'Somerset', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (645, 78, 'SOS', 'Southend-on-Sea', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (646, 31, 'SP', 'Sao Paulo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (647, 108, 'SP', 'La Spezia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (648, 108, 'SR', 'Syracuse', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (649, 78, 'SRY', 'Surrey', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (650, 67, 'SS', 'Guipúzcoa', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (651, 108, 'SS', 'Sassari', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (652, 56, 'ST', 'Central Bohemian Region (Stredoceský kraj)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (653, 57, 'ST', 'Saxony-Anhalt (Sachn-Anhalt)', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (654, 78, 'STB', 'Strabane', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (655, 78, 'STE', 'Stoke-on-Trent', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (656, 78, 'STG', 'Stirling', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (657, 78, 'STH', 'Southampton', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (658, 78, 'STN', 'Sutton', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (659, 78, 'STS', 'Staffordshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (660, 78, 'STT', 'Stockton-on-Tees', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (661, 78, 'STY', 'South Tyneside', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (662, 108, 'SV', 'Savona', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (663, 78, 'SWA', 'Swansea', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (664, 78, 'SWD', 'Swindon', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (665, 78, 'SWK', 'Southwark', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (666, 40, 'SZ', 'Schwyz', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (667, 9, 'TM', 'Tucuman', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (668, 67, 'T', 'Tarragona', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (669, 103, 'TA', 'Tipperary', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (670, 108, 'TA', 'Taranto', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (671, 137, 'TA', 'Tabasco', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (672, 78, 'TAM', 'Tameside', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (673, 67, 'TE', 'Teruel', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (674, 108, 'TE', 'Teramo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (675, 67, 'TF', 'Santa Cruz De Tenerife', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (676, 73, 'TF', 'French Southern Territories', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (677, 78, 'TFW', 'Telford and Wrekin', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (678, 40, 'TG', 'Thurgau', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (679, 57, 'TH', 'Thuringia (Thüringen)', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (680, 78, 'THR', 'Thurrock', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (681, 40, 'TI', 'Ticino', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (682, 137, 'TL', 'Tlaxcala', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (683, 137, 'TM', 'Tamaulipas', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (684, 108, 'TN', 'Trento', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (685, 225, 'TN', 'Tennessee', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (686, 31, 'TO', 'Tocantins', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (687, 67, 'TO', 'Toledo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (688, 108, 'TO', 'Turin', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (689, 78, 'TOB', 'Torbay', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (690, 78, 'TOF', 'Torfaen', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (691, 108, 'TP', 'Trapani', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (692, 108, 'TR', 'Terni', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (693, 78, 'TRF', 'Trafford', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (694, 15, 'TS', 'Tasmania', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (695, 108, 'TS', 'Trieste', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (696, 108, 'TV', 'Treviso', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (697, 78, 'TWH', 'Tower Hamlets', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (698, 225, 'TX', 'Texas', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (699, 9, 'CH', 'Chubut', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (700, 108, 'UD', 'Udine', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (701, 40, 'UR', 'Uri', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (702, 56, 'US', 'Usti nad Labem Region (Ústecký kraj)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (703, 159, 'UT', 'Utrecht', _binary '', NULL, '2018-05-22 21:53:27', '2018-05-22 21:53:27');
INSERT INTO `state`
VALUES (704, 225, 'UT', 'Utah', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (705, 9, 'TF', 'Tierra del Fuego', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (706, 67, 'V', 'Valencia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (707, 67, 'VA', 'Valladolid', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (708, 108, 'VA', 'Varese', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (709, 225, 'VA', 'Virginia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (710, 19, 'VB', 'Vlaams Brabant', _binary '\0', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (711, 108, 'VB', 'Verbano-Cusio-Ossola', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (712, 67, 'VC', 'Valenciana, Comunidad', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (713, 108, 'VC', 'Vercelli', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (714, 40, 'VD', 'Vaud', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (715, 108, 'VE', 'Venice', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (716, 78, 'VGL', 'Vale of Glamorgan', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (717, 15, 'VI', 'Victoria', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (718, 67, 'VI', 'Álava', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (719, 108, 'VI', 'Vicenza', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (720, 225, 'VI', 'Virgin Islands', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (721, 137, 'VL', 'Veracruz Llave', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (722, 108, 'VR', 'Verona', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (723, 40, 'VS', 'Valais', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (724, 108, 'VT', 'Viterbo', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (725, 225, 'VT', 'Vermont', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (726, 108, 'VV', 'Vibo Valentia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (727, 56, 'VY', 'Vysocina Region (kraj Vysocina)', _binary '', NULL, '2011-01-17 23:19:43',
        '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (728, 9, 'CN', 'Corrientes', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (729, 15, 'WA', 'Western Australia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (730, 225, 'WA', 'Washington', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (731, 78, 'WAR', 'Warwickshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (732, 78, 'WBK', 'West Berkshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (733, 103, 'WD', 'Waterford', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (734, 78, 'WDU', 'West Dunbartonshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (735, 73, 'WF', 'Wallis and Futuna', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (736, 78, 'WFT', 'Waltham Forest', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (737, 78, 'WGN', 'Wigan', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (738, 103, 'WH', 'Westmeath', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (739, 225, 'WI', 'Wisconsin', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (740, 78, 'WIL', 'Wiltshire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (741, 78, 'WKF', 'Wakefield', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (742, 78, 'WLL', 'Walsall', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (743, 78, 'WLN', 'West Lothian', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (744, 78, 'WLV', 'Wolverhampton', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (745, 78, 'WND', 'Wandsworth', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (746, 78, 'WNM', 'Windsor and Maidenhead', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (747, 78, 'WOK', 'Wokingham', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (748, 78, 'WOR', 'Worcestershire', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (749, 78, 'WRL', 'Wirral', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (750, 78, 'WRT', 'Warrington', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (751, 78, 'WRX', 'Wrexham', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (752, 78, 'WSM', 'Westminster', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (753, 78, 'WSX', 'West Sussex', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (754, 19, 'WV', 'West-Vlaanderen', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (755, 225, 'WV', 'West Virginia', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (756, 103, 'WW', 'Wicklow', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (757, 103, 'WX', 'Wexford', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (758, 225, 'WY', 'Wyoming', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (759, 9, 'CB', 'Cordoba', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (760, 9, 'JY', 'Jujuy', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (761, 137, 'YC', 'Yucatan', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (762, 78, 'YOR', 'York', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (763, 38, 'YT', 'Yukon Territory', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (764, 73, 'YT', 'Mayotte', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (765, 9, 'SC', 'Santa Cruz', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (766, 67, 'Z', 'Zaragoza', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (767, 67, 'ZA', 'Zamora', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (768, 159, 'ZE', 'Zeeland', _binary '', NULL, '2018-05-22 21:55:12', '2018-05-22 21:55:12');
INSERT INTO `state`
VALUES (769, 78, 'ZET', 'Shetland Islands', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (770, 40, 'ZG', 'Zug', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (771, 40, 'ZH', 'Zurich', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (772, 159, 'ZH', 'Zuid Holland', _binary '', NULL, '2018-05-22 21:55:34', '2018-05-22 21:55:34');
INSERT INTO `state`
VALUES (773, 56, 'ZL', 'Zlin Region (Zlínský kraj)', _binary '', NULL, '2011-01-17 23:19:43', '2011-01-17 23:59:16');
INSERT INTO `state`
VALUES (774, 137, 'ZT', 'Zacatecas', _binary '', NULL, '2014-02-24 15:31:48', '2014-02-24 15:31:48');
INSERT INTO `state`
VALUES (775, 238, 'EC', 'Eastern Cape', _binary '', NULL, '2020-03-23 13:37:46', NULL);
INSERT INTO `state`
VALUES (776, 238, 'FS', 'Free StateRepository', _binary '', NULL, '2020-03-23 13:37:46', NULL);
INSERT INTO `state`
VALUES (777, 238, 'GT', 'Gauteng', _binary '', NULL, '2020-03-23 13:37:46', NULL);
INSERT INTO `state`
VALUES (778, 238, 'NL', 'KwaZulu-Natal', _binary '', NULL, '2020-03-23 13:37:47', NULL);
INSERT INTO `state`
VALUES (779, 238, 'LP', 'Limpopo', _binary '', NULL, '2020-03-23 13:37:47', NULL);
INSERT INTO `state`
VALUES (780, 238, 'MP', 'Mpumalanga', _binary '', NULL, '2020-03-23 13:37:47', NULL);
INSERT INTO `state`
VALUES (781, 238, 'NC', 'Northern Cape', _binary '', NULL, '2020-03-23 13:37:47', NULL);
INSERT INTO `state`
VALUES (782, 238, 'NW', 'North-West', _binary '', NULL, '2020-03-23 13:37:47', NULL);
INSERT INTO `state`
VALUES (783, 238, 'WC', 'Western Cape', _binary '', NULL, '2020-03-23 13:37:47', NULL);


update meta.country
set date_created = now();
update meta.country
set last_updated = now();
update meta.state
set date_created = now();
update meta.state
set last_updated = now();

ALTER TABLE `meta`.`country`
    DROP COLUMN `active`;

ALTER TABLE `meta`.`state`
    DROP COLUMN `sort`,
    DROP COLUMN `active`;

