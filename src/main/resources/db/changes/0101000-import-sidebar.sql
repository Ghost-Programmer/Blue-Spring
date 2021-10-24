--liquibase formatted sql

--changeset jmiller:0101000

INSERT INTO `documents`.`menu` (`id`, `name`, `icon`, `delete_lock`,`date_created`,`last_updated`) VALUES ('1', 'sidebar','',1, now(), now());

INSERT INTO `documents`.`menu_item` (`id`,`menu_id`, `sort`,`name`, `icon`,`route`, `url`,`delete_lock`,`date_created`,`last_updated`)
VALUES ('1','1', '10','dashboard','dashboard','dashboard',null,1, now(), now());

INSERT INTO `documents`.`menu_item` (`id`,`menu_id`, `sort`,`name`, `icon`,`route`, `url`,`delete_lock`,`date_created`,`last_updated`)
VALUES ('2','1', '20','calendar','calendar_today','calendar',null,1, now(), now());

INSERT INTO `documents`.`menu_item` (`id`,`menu_id`, `sort`,`name`, `icon`,`route`, `url`,`delete_lock`,`date_created`,`last_updated`)
VALUES ('3','1', '30','documents','cloud','documents',null,1, now(), now());

INSERT INTO `documents`.`menu_item` (`id`,`menu_id`, `sort`,`name`, `icon`,`route`, `url`,`delete_lock`,`date_created`,`last_updated`)
VALUES ('4','1', '40','Settings','settings','admin',null,1, now(), now());

insert into `documents`.`menu_item_access` (`menu_item_id`, `role_id`,`date_created`,`last_updated`)
values ('3', '1', now(), now());

insert into `documents`.`menu_item_access` (`menu_item_id`, `role_id`,`date_created`,`last_updated`)
values ('4', '9', now(), now());
