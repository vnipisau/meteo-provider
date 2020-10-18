--liquibase formatted sql

--changeset woodapiary:updatetables1

 ALTER TABLE ya_forecast MODIFY COLUMN `date` DATE DEFAULT NULL NULL COMMENT 'Дата прогноза в формате ГГГГ-ММ-ДД (локальное)';
 
--changeset woodapiary:updatetables2
 
 ALTER TABLE ya_message DROP COLUMN `url`;
 ALTER TABLE ya_message DROP COLUMN `lon`;
 ALTER TABLE ya_message DROP COLUMN `lat`;