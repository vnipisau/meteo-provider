--liquibase formatted sql

--changeset woodapiary:updatetables1

 ALTER TABLE forecast MODIFY COLUMN `date` DATE DEFAULT NULL NULL COMMENT 'Дата прогноза в формате ГГГГ-ММ-ДД (локальное)';
