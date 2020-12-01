insert into nrha.user (id, username,password,enabled,account_expired,password_change_required,account_locked,account_verified,account_complete,account_refreshed,date_created,last_updated) select id, username,password,enabled,account_expired,password_change_required,account_locked,account_verified,account_complete,account_refreshed,date_created,last_updated from core.user;

# TODO: Change to correct roles
insert into nrha.user_security_role (user_id,security_role_id,date_created,last_updated) select id,1,now(),now() from nrha.user;
insert into nrha.user_security_role (user_id,security_role_id,date_created,last_updated) select id,4,now(),now() from nrha.user;
