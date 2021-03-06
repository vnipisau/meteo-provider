--liquibase formatted sql

--changeset woodapiary:createtables1
CREATE TABLE `source` (
  `source_id`            bigint unsigned       NOT NULL AUTO_INCREMENT              COMMENT 'id источника погоды',
  `modified`             timestamp             NOT NULL DEFAULT current_timestamp() 
                                               ON UPDATE current_timestamp()        COMMENT 'Время создания записи',
  `source_name`          varchar(64)           NOT NULL                             COMMENT 'название',
  `provider`             varchar(64)           NOT NULL                             COMMENT 'провайдер',
  `api_version`          varchar(16)           DEFAULT NULL                         COMMENT 'версия api',
  `url`                  varchar(255)          NOT NULL                             COMMENT 'url сервиса',
  `lon`                  DECIMAL(5,2)          DEFAULT NULL                         COMMENT 'долгота места измерений',
  `lat`                  DECIMAL(5,2)          DEFAULT NULL                         COMMENT 'широта места измерений',
  `geoname`              varchar(64)           DEFAULT NULL                         COMMENT 'название места измерений',
  `geoname_id`           int unsigned          DEFAULT NULL                         COMMENT 'id места измерений',
  `enabled`              tinyint(1)            NOT NULL DEFAULT 1,
 
  PRIMARY KEY (`source_id`)
) COMMENT='Источник данных о погоде';

--changeset woodapiary:createtables2
CREATE TABLE `ya_message` (
  `message_id`           bigint unsigned       NOT NULL AUTO_INCREMENT              COMMENT 'id',
  `now`                  timestamp             NULL DEFAULT NULL                    COMMENT 'Время сервера погоды в формате Unixtime',
  `now_dt`               timestamp             NULL DEFAULT NULL                    COMMENT 'Время сервера погоды utc',
  `modified`             timestamp             NOT NULL DEFAULT current_timestamp() 
                                               ON UPDATE current_timestamp()        COMMENT 'Время создания записи',
  `source_id`            bigint unsigned                                            COMMENT 'id источника погоды',
  `url`                  varchar(255)          DEFAULT NULL                         COMMENT 'url сервиса из info',
  `lon`                  DECIMAL(5,2)          DEFAULT NULL                         COMMENT 'долгота места измерений info',
  `lat`                  DECIMAL(5,2)          DEFAULT NULL                         COMMENT 'широта места измерений info',
  
  PRIMARY KEY (`message_id`),
  KEY `message_FK` (`source_id`),
  CONSTRAINT `message_FK` FOREIGN KEY (`source_id`) REFERENCES `source` (`source_id`)
)  COMMENT='Сообщения о погоде';

--changeset woodapiary:createtables3
CREATE TABLE `ya_forecast` (
  `forecast_id`          bigint unsigned       NOT NULL AUTO_INCREMENT COMMENT 'id прогноза',
  `message_id`           bigint unsigned                               COMMENT 'id сообщения о погоде',
  `date`                 timestamp             NULL DEFAULT NULL       COMMENT 'Дата прогноза в формате ГГГГ-ММ-ДД (локальное)',
  `date_ts`              time                  NULL DEFAULT NULL       COMMENT 'Дата прогноза в формате Unixtime',
  `sunrise`              time                  NULL DEFAULT NULL       COMMENT 'Время восхода Солнца,локальное время',
  `sunset`               time                  DEFAULT NULL            COMMENT 'Время заката Солнца,локальное время',
  `moon_code`            tinyint(3) unsigned   DEFAULT NULL            COMMENT 'Код фазы Луны',
  `moon_text`            varchar(100)          DEFAULT NULL            COMMENT 'Текстовый код для фазы Луны',
  `week`                 tinyint(3) unsigned              DEFAULT NULL            COMMENT 'Порядковый номер недели',
  
  PRIMARY KEY (`forecast_id`),
  CONSTRAINT `forecast_FK` FOREIGN KEY (`message_id`) REFERENCES `ya_message` (`message_id`)
) COMMENT='Прогнозная погода - заголовок';

--changeset woodapiary:createtables4
CREATE TABLE `ya_part` (
  `part_id`              bigint unsigned       NOT NULL AUTO_INCREMENT COMMENT 'id прогнозного значения',
  `part_name`            varchar(100)          DEFAULT NULL            COMMENT 'Название времени суток',
  `temp_min`             smallint              DEFAULT NULL            COMMENT 'Температура (°C) min',
  `temp_max`             smallint              DEFAULT NULL            COMMENT 'Температура (°C) max',
  `temp_avg`             smallint              DEFAULT NULL            COMMENT 'Температура (°C) avg',
  `feels_like`           smallint              DEFAULT NULL            COMMENT 'Ощущаемая температура (°C)',
  `icon`                 varchar(100)          DEFAULT NULL            COMMENT 'Код иконки погоды.',
  `conditionw`           varchar(100)          DEFAULT NULL            COMMENT 'Код расшифровки погодного описания',
  `wind_speed`           DECIMAL(4,1) unsigned DEFAULT NULL            COMMENT 'Скорость ветра (в м/с ) ',
  `wind_gust`            DECIMAL(4,1) unsigned DEFAULT NULL            COMMENT 'Скорость порывов ветра (в м/с) ',
  `wind_dir`             varchar(3)            DEFAULT NULL            COMMENT 'Направление ветра.',
  `pressure_mm`          smallint unsigned     DEFAULT NULL            COMMENT 'Давление (в мм) (mm)',
  `pressure_pa`          smallint unsigned     DEFAULT NULL            COMMENT 'Давление (в гектопаскалях) (mbar)',
  `humidity`             smallint unsigned     DEFAULT NULL            COMMENT 'Влажность воздуха (в процентах)',
  `daytime`              varchar(1)            DEFAULT NULL            COMMENT 'Светлое или темное время суток',
  `polar`                smallint unsigned     DEFAULT NULL            COMMENT 'Признак полярного дня или ночи',
  `prec_mm`              DECIMAL(4,1) unsigned DEFAULT NULL            COMMENT 'Прогнозируемое количество осадков (в мм)',
  `prec_period`          smallint(5) unsigned  DEFAULT NULL            COMMENT 'Прогнозируемый период осадков (в минутах)',
  `prec_prob`            smallint(5) unsigned  DEFAULT NULL            COMMENT 'Вероятность выпадения осадков',
  `forecast_id`          bigint unsigned                               COMMENT 'id прогнозного значения',
  
   PRIMARY KEY (`part_id`),
   KEY `part_FK` (`forecast_id`),
   CONSTRAINT `part_FK` FOREIGN KEY (`forecast_id`) REFERENCES `ya_forecast` (`forecast_id`)
)  COMMENT='Прогнозная погода - на дату';

--changeset woodapiary:createtables5
CREATE TABLE `ya_fact` (
  `fact_id`              bigint unsigned       NOT NULL AUTO_INCREMENT              COMMENT 'id фактического значения',
  `temp`                 smallint              DEFAULT NULL                         COMMENT 'Температура (°C)',
  `feels_like`           smallint              DEFAULT NULL                         COMMENT 'Ощущаемая температура (°C)',
  `temp_water`           smallint              DEFAULT NULL                         COMMENT 'Температура воды (°C)',
  `icon`                 varchar(100)          DEFAULT NULL                         COMMENT 'Код иконки погоды.',
  `conditionw`           varchar(100)          DEFAULT NULL                         COMMENT 'Код расшифровки погодного описания',
  `wind_speed`           DECIMAL(4,1) unsigned DEFAULT NULL                         COMMENT 'Скорость ветра (в м/с ) ',
  `wind_gust`            DECIMAL(4,1) unsigned DEFAULT NULL                         COMMENT 'Скорость порывов ветра (в м/с) ',
  `wind_dir`             varchar(3)            DEFAULT NULL                         COMMENT 'Направление ветра.',
  `pressure_mm`          smallint unsigned     DEFAULT NULL                         COMMENT 'Давление (в мм) (mm)',
  `pressure_pa`          smallint unsigned     DEFAULT NULL                         COMMENT 'Давление (в гектопаскалях) (mbar)',
  `humidity`             smallint unsigned     DEFAULT NULL                         COMMENT 'Влажность воздуха (в процентах)',
  `daytime`              varchar(1)            DEFAULT NULL                         COMMENT 'Светлое или темное время суток',
  `polar`                smallint unsigned     DEFAULT NULL                         COMMENT 'Признак полярного дня или ночи',
  `season`               varchar(30)           DEFAULT NULL                         COMMENT 'Направление ветра',
  `obs_time`             timestamp             NULL DEFAULT NULL                    COMMENT 'Время замера погодных данных в формате Unixtime utc',
  `message_id`           bigint unsigned                                            COMMENT 'id сообщения',
  
   PRIMARY KEY (`fact_id`),
   KEY `fact_FK` (`message_id`),
   CONSTRAINT `fact_FK` FOREIGN KEY (`message_id`) REFERENCES `ya_message` (`message_id`)
)  COMMENT='Фактическая погода';

--changeset woodapiary:createtables6
CREATE TABLE `ws_message` (
  `message_id`           bigint unsigned     NOT NULL AUTO_INCREMENT              COMMENT 'id',
  `modified`             timestamp           NOT NULL DEFAULT current_timestamp() 
                                             ON UPDATE current_timestamp()        COMMENT 'Время создания записи',
  `source_id`            bigint unsigned                                          COMMENT 'id источника погоды',
  
  PRIMARY KEY (`message_id`),
  KEY `wsmessage_FK` (`source_id`),
  CONSTRAINT `wsmessage_FK` FOREIGN KEY (`source_id`) REFERENCES `source` (`source_id`)
)  COMMENT='Сообщения о погоде';

--changeset woodapiary:createtables7
CREATE TABLE `ws_fact` (
  `fact_id`              bigint unsigned     NOT NULL AUTO_INCREMENT     COMMENT 'id фактического значения',
  `observation_time`     time                NULL DEFAULT NULL           COMMENT 'Время замера погодных данных в формате Unixtime utc',
  `temperature`          smallint            DEFAULT NULL                COMMENT 'Температура (°C)',
  `weather_code`smallint unsigned            DEFAULT NULL                COMMENT '',
  `weather_icons`        varchar(255)        DEFAULT NULL                COMMENT 'Код иконки погоды.',
  `weather_descriptions` varchar(255)        DEFAULT NULL                COMMENT '',
  `wind_speed`           smallint unsigned   DEFAULT NULL                COMMENT 'Скорость ветра (в м/с ) ',
  `wind_degree`          smallint unsigned   DEFAULT NULL                COMMENT '',
  `wind_dir`             varchar(3)          DEFAULT NULL                COMMENT 'Направление ветра.',
  `pressure`             smallint unsigned   DEFAULT NULL                COMMENT 'Давление (в гектопаскалях) (mbar)',
  `precip`               smallint unsigned   DEFAULT NULL                COMMENT '',
  `humidity`             smallint unsigned   DEFAULT NULL                COMMENT 'Влажность воздуха (в процентах)',
  `cloudcover`           smallint unsigned   DEFAULT NULL                COMMENT '',
  `feelslike`            smallint            DEFAULT NULL                COMMENT 'Ощущаемая температура (°C)',
  `uv_index`             smallint unsigned   DEFAULT NULL                COMMENT '',
  `visibility`           smallint unsigned   DEFAULT NULL                COMMENT '',
  `message_id`           bigint unsigned                                 COMMENT 'id сообщения',
      
   PRIMARY KEY (`fact_id`),
   KEY `wsfact_FK` (`message_id`),
   CONSTRAINT `wsfact_FK` FOREIGN KEY (`message_id`) REFERENCES `ws_message` (`message_id`)
)  

--changeset woodapiary:createtables8
CREATE TABLE `ow_weather` (
  `id`           smallint unsigned DEFAULT NULL              COMMENT '',
  `main`         varchar(255)      DEFAULT NULL              COMMENT '',
  `description`  varchar(255)      DEFAULT NULL              COMMENT '',
  `icon`         varchar(255)      DEFAULT NULL              COMMENT 'Код иконки погоды.',
  
   PRIMARY KEY (`id`)
)

--changeset woodapiary:createtables9
CREATE TABLE `ow_message` (
  `message_id`   bigint unsigned   NOT NULL AUTO_INCREMENT               COMMENT 'id',
  `modified`     timestamp         NOT NULL DEFAULT current_timestamp() 
                                   ON UPDATE current_timestamp()         COMMENT 'Время создания записи',
  `source_id`    bigint unsigned                                         COMMENT 'id источника погоды',
  
  PRIMARY KEY (`message_id`),
  KEY `owmessage_FK` (`source_id`),
  CONSTRAINT `owmessage_FK` FOREIGN KEY (`source_id`) REFERENCES `source` (`source_id`)
)  COMMENT='Сообщения о погоде';
 
--changeset woodapiary:createtables10
CREATE TABLE `ow_alert` (
  `alert_id`     bigint unsigned   NOT NULL AUTO_INCREMENT   COMMENT 'id',
  `sender_name`  varchar(255)      DEFAULT NULL              COMMENT '',
  `event`        varchar(255)      DEFAULT NULL              COMMENT '',
  `description`  varchar(1024)      DEFAULT NULL             COMMENT '',
  `start`        timestamp         NULL DEFAULT NULL         COMMENT '',
  `end`          timestamp         NULL DEFAULT NULL         COMMENT '',
  `message_id`   bigint unsigned                             COMMENT 'id сообщения',
  
   PRIMARY KEY (`alert_id`),
   KEY `owalert_FK` (`message_id`),
   CONSTRAINT `owalert_FK` FOREIGN KEY (`message_id`) REFERENCES `ow_message` (`message_id`)
) 

--changeset woodapiary:createtables11
CREATE TABLE `ow_fact` (
  `fact_id`      bigint unsigned   NOT NULL AUTO_INCREMENT   COMMENT 'id фактического значения',
  `dt`           timestamp              NULL DEFAULT NULL         COMMENT 'Время замера погодных данных в формате Unixtime utc',
  `sunrise`      timestamp              NULL DEFAULT NULL         COMMENT 'Время восхода в формате Unixtime utc',
  `sunset`       timestamp              NULL DEFAULT NULL         COMMENT 'Время заката в формате Unixtime utc',
  `temp`         DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Температура (°C)',
  `feels_like`   DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Ощущаемая температура (°C)',
  `pressure`     smallint unsigned DEFAULT NULL              COMMENT 'Давление (в гектопаскалях) (mbar)',
  `humidity`     smallint unsigned DEFAULT NULL              COMMENT 'Влажность воздуха (в процентах)',
  `dew_point`    DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Точка росы (°C)',
  `uvi`          DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `clouds`       smallint unsigned DEFAULT NULL              COMMENT '',
  `visibility`   smallint unsigned DEFAULT NULL              COMMENT '',
  `wind_speed`   DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Скорость ветра (в м/с ) ',
  `wind_deg`     smallint unsigned DEFAULT NULL              COMMENT '',
  `wind_gust`    DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `rain1h`       DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `snow1h`       DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `message_id`   bigint unsigned                             COMMENT 'id сообщения',
  
   PRIMARY KEY (`fact_id`),
   KEY `owfact_FK` (`message_id`),
   CONSTRAINT `owfact_FK` FOREIGN KEY (`message_id`) REFERENCES `ow_message` (`message_id`)
) 

--changeset woodapiary:createtables12
CREATE TABLE `ow_daily` (
  `daily_id`         bigint unsigned   NOT NULL AUTO_INCREMENT   COMMENT 'id фактического значения',
  `dt`               timestamp         NULL DEFAULT NULL         COMMENT 'Время замера погодных данных в формате Unixtime utc',
  `sunrise`          timestamp         NULL DEFAULT NULL         COMMENT 'Время восхода в формате Unixtime utc',
  `sunset`           timestamp         NULL DEFAULT NULL         COMMENT 'Время заката в формате Unixtime utc',
  `day_temp`         DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Температура (°C)',
  `min_temp`         DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Температура (°C)',
  `max_temp`         DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Температура (°C)',
  `night_temp`       DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Температура (°C)',
  `eve_temp`         DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Температура (°C)',
  `morn_temp`        DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Температура (°C)',  
  `day_feels_like`   DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Ощущаемая температура (°C)',
  `night_feels_like` DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Ощущаемая температура (°C)',
  `eve_feels_like`   DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Ощущаемая температура (°C)',
  `morn_feels_like`  DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Ощущаемая температура (°C)',   
  `pressure`         smallint unsigned DEFAULT NULL              COMMENT 'Давление (в гектопаскалях) (mbar)',
  `humidity`         smallint unsigned DEFAULT NULL              COMMENT 'Влажность воздуха (в процентах)',
  `dew_point`        DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Точка росы (°C)',
  `uvi`              DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `clouds`           smallint unsigned DEFAULT NULL              COMMENT '',
  `visibility`       smallint unsigned DEFAULT NULL              COMMENT '',
  `wind_speed`       DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Скорость ветра (в м/с ) ',
  `wind_deg`         smallint unsigned DEFAULT NULL              COMMENT '',
  `wind_gust`        DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `pop`              DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `rain1h`           DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `snow1h`           DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `message_id`       bigint unsigned                             COMMENT 'id сообщения',
  
   PRIMARY KEY (`daily_id`),
   KEY `owdaily_FK` (`message_id`),
   CONSTRAINT `owdaily_FK` FOREIGN KEY (`message_id`) REFERENCES `ow_message` (`message_id`)
) 

--changeset woodapiary:createtables13
CREATE TABLE `ow_hourly` (
  `hourly_id`        bigint unsigned   NOT NULL AUTO_INCREMENT   COMMENT 'id фактического значения',
  `dt`               timestamp         NULL DEFAULT NULL         COMMENT 'Время замера погодных данных в формате Unixtime utc',
  `temp`             DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Температура (°C)',
  `feels_like`       DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Ощущаемая температура (°C)',  
  `pressure`         smallint unsigned DEFAULT NULL              COMMENT 'Давление (в гектопаскалях) (mbar)',
  `humidity`         smallint unsigned DEFAULT NULL              COMMENT 'Влажность воздуха (в процентах)',
  `dew_point`        DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Точка росы (°C)',
  `clouds`           smallint unsigned DEFAULT NULL              COMMENT '',
  `visibility`       smallint unsigned DEFAULT NULL              COMMENT '',
  `wind_speed`       DECIMAL(5,2)      DEFAULT NULL              COMMENT 'Скорость ветра (в м/с ) ',
  `wind_deg`         smallint unsigned DEFAULT NULL              COMMENT '',
  `wind_gust`        DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `pop`              DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `rain1h`           DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `snow1h`           DECIMAL(5,2)      DEFAULT NULL              COMMENT '',
  `message_id`       bigint unsigned                             COMMENT 'id сообщения',
  
   PRIMARY KEY (`hourly_id`),
   KEY `owhourly_FK` (`message_id`),
   CONSTRAINT `owhourly_FK` FOREIGN KEY (`message_id`) REFERENCES `ow_message` (`message_id`)
) 

--changeset woodapiary:createtables14
CREATE TABLE `ow_weather_fact` (
  `weather_id`       smallint(6) unsigned  DEFAULT NULL          COMMENT '',
  `fact_id`          bigint(20) unsigned   DEFAULT NULL          COMMENT '',
  
  PRIMARY KEY (`weather_id`,`fact_id`),
  KEY `ow_weather_fact_FK1` (`fact_id`),
  CONSTRAINT `ow_weather_fact_FK1` FOREIGN KEY (`fact_id`) REFERENCES `ow_fact` (`fact_id`),
  KEY `ow_weather_fact_FK2` (`weather_id`),
  CONSTRAINT `ow_weather_fact_FK2` FOREIGN KEY (`weather_id`) REFERENCES `ow_weather` (`id`)
)

--changeset woodapiary:createtables15
CREATE TABLE `ow_weather_daily` (
  `weather_id`       smallint(6) unsigned  DEFAULT NULL          COMMENT '',
  `daily_id`         bigint(20) unsigned   DEFAULT NULL          COMMENT '',
  
  PRIMARY KEY (`weather_id`,`daily_id`),
  KEY `ow_weather_daily_FK1` (`daily_id`),
  CONSTRAINT `ow_weather_daily_FK1` FOREIGN KEY (`daily_id`) REFERENCES `ow_daily` (`daily_id`),
  KEY `ow_weather_daily_FK2` (`weather_id`),
  CONSTRAINT `ow_weather_daily_FK2` FOREIGN KEY (`weather_id`) REFERENCES `ow_weather` (`id`)
)

--changeset woodapiary:createtables16
CREATE TABLE `ow_weather_hourly` (
  `weather_id`       smallint(6) unsigned  DEFAULT NULL          COMMENT '',
  `hourly_id`        bigint(20) unsigned   DEFAULT NULL          COMMENT '',
  
  PRIMARY KEY (`weather_id`,`hourly_id`),
  KEY `ow_weather_hourly_FK1` (`hourly_id`),
  CONSTRAINT `ow_weather_hourly_FK1` FOREIGN KEY (`hourly_id`) REFERENCES `ow_hourly` (`hourly_id`),
  KEY `ow_weather_hourly_FK2` (`weather_id`),
  CONSTRAINT `ow_weather_hourly_FK2` FOREIGN KEY (`weather_id`) REFERENCES `ow_weather` (`id`)
)







