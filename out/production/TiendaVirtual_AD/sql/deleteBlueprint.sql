
delete from usuarios where id > 0;
alter table usuarios auto_increment = 0;