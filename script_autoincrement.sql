use issuemanager;
set foreign_key_checks = 0;
alter table issue MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT;
set foreign_key_checks = 1;