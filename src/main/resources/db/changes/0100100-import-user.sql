--liquibase formatted sql

--changeset jmiller:0100100

insert into user.user (id, username,password,enabled,account_expired,password_change_required,account_locked,account_verified,account_complete,account_refreshed,date_created,last_updated) select id, username,password,enabled,account_expired,password_change_required,account_locked,account_verified,account_complete,account_refreshed,date_created,last_updated from core.user;

# TODO: Change to correct roles
insert into user.user_security_role (user_id,security_role_id,date_created,last_updated) select id,1,now(),now() from core.user;
insert into user.user_security_role (user_id,security_role_id,date_created,last_updated) select id,4,now(),now() from core.user;
update user.user set account_expired = 0;
update user.user set account_locked = 0;

INSERT INTO `user`.`user_security_role` (`user_id`, `security_role_id`, `date_created`, `last_updated`) VALUES ('1', '2', now(), now());
INSERT INTO `user`.`user_security_role` (`user_id`, `security_role_id`, `date_created`, `last_updated`) VALUES ('1', '4', now(), now());
INSERT INTO `user`.`user_security_role` (`user_id`, `security_role_id`, `date_created`, `last_updated`) VALUES ('1', '5', now(), now());
INSERT INTO `user`.`user_security_role` (`user_id`, `security_role_id`, `date_created`, `last_updated`) VALUES ('1', '6', now(), now());
