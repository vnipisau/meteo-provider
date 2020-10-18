--liquibase formatted sql

--changeset woodapiary:initdata1

 INSERT  INTO  source  (source_name, provider, url,enabled,lat,lon)  VALUES  ('yandex-moscow','yandex','https://api.weather.yandex.ru/v1/informers/',true,55.75,37.6);

--changeset woodapiary:initdata2
 ALTER TABLE ya_message AUTO_INCREMENT=1000;
 ALTER TABLE ya_forecast AUTO_INCREMENT=1000;
 ALTER TABLE ya_part AUTO_INCREMENT=1000;
 ALTER TABLE ya_fact AUTO_INCREMENT=1000;
 ALTER TABLE ws_message AUTO_INCREMENT=1000;
 ALTER TABLE ws_fact AUTO_INCREMENT=1000;
 
--changeset woodapiary:initdata4

 INSERT  INTO  source  (source_name, provider, url,enabled,lat,lon)  VALUES  ('yandex-svinchus','yandex','https://api.weather.yandex.ru/v1/informers/',true,54.571705,41.083740);
 INSERT  INTO  source  (source_name, provider, url,enabled,lat,lon)  VALUES  ('yandex-kaliningrad','yandex','https://api.weather.yandex.ru/v1/informers/',true,54.710157,20.510137);

--changeset woodapiary:initdata5
 INSERT  INTO  source  (source_name, provider, url,enabled,lat,lon)  VALUES  ('weatherstack-moscow','weatherstack','http://api.weatherstack.com/current/',true,55.75,37.6);
 INSERT  INTO  source  (source_name, provider, url,enabled,lat,lon)  VALUES  ('weatherstack-svinchus','weatherstack','http://api.weatherstack.com/current/',true,54.571705,41.083740);
 INSERT  INTO  source  (source_name, provider, url,enabled,lat,lon)  VALUES  ('weatherstack-kaliningrad','weatherstack','http://api.weatherstack.com/current/',true,54.710157,20.510137);
