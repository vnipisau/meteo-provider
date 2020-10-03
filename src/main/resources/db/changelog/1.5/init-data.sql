--liquibase formatted sql

--changeset woodapiary:initdata1

 INSERT  INTO  source  (source_name, provider, url,enabled,lat,lon)  VALUES  ('yandex-moscow','yandex','https://api.weather.yandex.ru/v1/informers/',true,55.75,37.6);
