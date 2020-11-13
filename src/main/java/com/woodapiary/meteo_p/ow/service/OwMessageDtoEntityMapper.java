/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo_p.ow.dto.OwAlertDto;
import com.woodapiary.meteo_p.ow.dto.OwCurrentDto;
import com.woodapiary.meteo_p.ow.dto.OwDailyDto;
import com.woodapiary.meteo_p.ow.dto.OwHourlyDto;
import com.woodapiary.meteo_p.ow.dto.OwMessageDto;
import com.woodapiary.meteo_p.ow.dto.OwRainDto;
import com.woodapiary.meteo_p.ow.dto.OwWeatherDto;
import com.woodapiary.meteo_p.ow.entities.OwAlert;
import com.woodapiary.meteo_p.ow.entities.OwDaily;
import com.woodapiary.meteo_p.ow.entities.OwFact;
import com.woodapiary.meteo_p.ow.entities.OwHourly;
import com.woodapiary.meteo_p.ow.entities.OwMessage;
import com.woodapiary.meteo_p.ow.entities.OwWeather;

@Component
public class OwMessageDtoEntityMapper {

    @Autowired
    private ModelMapper modelMapper;
    TypeMap<OwCurrentDto, OwFact> typeMapFactDtoToFact;
    TypeMap<OwFact, OwCurrentDto> typeMapFactDtoFromFact;
    TypeMap<OwDaily, OwDailyDto> typeMapDailyDtoFromDaily;
    TypeMap<OwDailyDto, OwDaily> typeMapDailyDtoToDaily;
    TypeMap<OwHourly, OwHourlyDto> typeMapHourlyDtoFromHourly;
    TypeMap<OwHourlyDto, OwHourly> typeMapHourlyDtoToHourly;
    TypeMap<OwMessage, OwMessageDto> typeMapMessageDtoFromMessage;
    TypeMap<OwMessageDto, OwMessage> typeMapMessageDtoToMessage;

    Converter<OwRainDto, Double> rainToDouble = new AbstractConverter<>() {
        @Override
        protected Double convert(OwRainDto source) {
            if (source == null) {
                return null;
            }
            return source.getM1h();
        }
    };

    Converter<Double, OwRainDto> rainFromDouble = new AbstractConverter<>() {
        @Override
        protected OwRainDto convert(Double source) {
            if (source == null) {
                return null;
            }
            final OwRainDto res = new OwRainDto();
            res.set1h(source);
            return res;
        }
    };

    Converter<List<OwWeather>, List<OwWeatherDto>> weatherListDtoFromWeatherList = new AbstractConverter<>() {
        @Override
        protected List<OwWeatherDto> convert(List<OwWeather> source) {
            if (source == null) {
                return null;
            }
            return weatherListDtoFromWeatherList(source);
        }
    };

    Converter<List<OwWeatherDto>, List<OwWeather>> weatherListDtoToWeatherList = new AbstractConverter<>() {
        @Override
        protected List<OwWeather> convert(List<OwWeatherDto> source) {
            if (source == null) {
                return null;
            }
            return weatherListDtoToWeatherList(source);
        }
    };

    Converter<List<OwDaily>, List<OwDailyDto>> dailyListDtoFromDailyList = new AbstractConverter<>() {
        @Override
        protected List<OwDailyDto> convert(List<OwDaily> source) {
            if (source == null) {
                return null;
            }
            return dailyListDtoFromDailyList(source);
        }
    };

    Converter<List<OwDailyDto>, List<OwDaily>> dailyListDtoToDailyList = new AbstractConverter<>() {
        @Override
        protected List<OwDaily> convert(List<OwDailyDto> source) {
            if (source == null) {
                return null;
            }
            return dailyListDtoToDailyList(source);
        }
    };

    Converter<List<OwHourly>, List<OwHourlyDto>> hourlyListDtoFromHourlyList = new AbstractConverter<>() {
        @Override
        protected List<OwHourlyDto> convert(List<OwHourly> source) {
            if (source == null) {
                return null;
            }
            return hourlyListDtoFromHourlyList(source);
        }
    };

    Converter<List<OwHourlyDto>, List<OwHourly>> hourlyListDtoToHourlyList = new AbstractConverter<>() {
        @Override
        protected List<OwHourly> convert(List<OwHourlyDto> source) {
            if (source == null) {
                return null;
            }
            return hourlyListDtoToHourlyList(source);
        }
    };

    Converter<List<OwAlert>, List<OwAlertDto>> alertListDtoFromAlertList = new AbstractConverter<>() {
        @Override
        protected List<OwAlertDto> convert(List<OwAlert> source) {
            if (source == null) {
                return null;
            }
            return alertListDtoFromAlertList(source);
        }
    };

    Converter<List<OwAlertDto>, List<OwAlert>> alertListDtoToAlertList = new AbstractConverter<>() {
        @Override
        protected List<OwAlert> convert(List<OwAlertDto> source) {
            if (source == null) {
                return null;
            }
            return alertListDtoToAlertList(source);
        }
    };

    @PostConstruct
    public void init() {
        typeMapMessageDtoToMessage = modelMapper.createTypeMap(OwMessageDto.class, OwMessage.class);
        typeMapMessageDtoToMessage.addMappings(mapper -> mapper.using(alertListDtoToAlertList).map(OwMessageDto::getAlerts, OwMessage::setAlerts));
        typeMapMessageDtoToMessage.addMappings(mapper -> mapper.using(dailyListDtoToDailyList).map(OwMessageDto::getDaily, OwMessage::setDaily));
        typeMapMessageDtoToMessage.addMappings(mapper -> mapper.using(hourlyListDtoToHourlyList).map(OwMessageDto::getHourly, OwMessage::setHourly));

        typeMapMessageDtoFromMessage = modelMapper.createTypeMap(OwMessage.class, OwMessageDto.class);
        typeMapMessageDtoFromMessage.addMappings(mapper -> mapper.using(alertListDtoFromAlertList).map(OwMessage::getAlerts, OwMessageDto::setAlerts));
        typeMapMessageDtoFromMessage.addMappings(mapper -> mapper.using(dailyListDtoFromDailyList).map(OwMessage::getDaily, OwMessageDto::setDaily));
        typeMapMessageDtoFromMessage.addMappings(mapper -> mapper.using(hourlyListDtoFromHourlyList).map(OwMessage::getHourly, OwMessageDto::setHourly));

        typeMapFactDtoToFact = modelMapper.createTypeMap(OwCurrentDto.class, OwFact.class);
        typeMapFactDtoToFact.addMappings(mapper -> mapper.using(rainToDouble).map(OwCurrentDto::getRain, OwFact::setRain1h));
        typeMapFactDtoToFact.addMappings(mapper -> mapper.using(rainToDouble).map(OwCurrentDto::getSnow, OwFact::setSnow1h));
        typeMapFactDtoToFact.addMappings(mapper -> mapper.using(weatherListDtoToWeatherList).map(OwCurrentDto::getWeather, OwFact::setWeather));

        typeMapFactDtoFromFact = modelMapper.createTypeMap(OwFact.class, OwCurrentDto.class);
        typeMapFactDtoFromFact.addMappings(mapper -> mapper.using(rainFromDouble).map(OwFact::getRain1h, OwCurrentDto::setRain));
        typeMapFactDtoFromFact.addMappings(mapper -> mapper.using(rainFromDouble).map(OwFact::getSnow1h, OwCurrentDto::setSnow));
        typeMapFactDtoFromFact.addMappings(mapper -> mapper.using(weatherListDtoFromWeatherList).map(OwFact::getWeather, OwCurrentDto::setWeather));

        typeMapDailyDtoToDaily = modelMapper.createTypeMap(OwDailyDto.class, OwDaily.class);
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.map(src -> src.getTemp().getDay(), OwDaily::setDayTemp));
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.map(src -> src.getTemp().getEve(), OwDaily::setEveTemp));
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.map(src -> src.getTemp().getNight(), OwDaily::setNightTemp));
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.map(src -> src.getTemp().getMorn(), OwDaily::setMornTemp));
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.map(src -> src.getTemp().getMin(), OwDaily::setMinTemp));
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.map(src -> src.getTemp().getMax(), OwDaily::setMaxTemp));
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.map(src -> src.getFeelsLike().getDay(), OwDaily::setDayFeelsLike));
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.map(src -> src.getFeelsLike().getEve(), OwDaily::setEveFeelsLike));
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.map(src -> src.getFeelsLike().getNight(), OwDaily::setNightFeelsLike));
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.map(src -> src.getFeelsLike().getMorn(), OwDaily::setMornFeelsLike));
        typeMapDailyDtoToDaily.addMappings(mapper -> mapper.using(weatherListDtoToWeatherList).map(OwDailyDto::getWeather, OwDaily::setWeather));

        typeMapDailyDtoFromDaily = modelMapper.createTypeMap(OwDaily.class, OwDailyDto.class);
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.map(OwDaily::getDayTemp, (dst, v) -> dst.getTemp().setDay((Double) v)));
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.map(OwDaily::getEveTemp, (dst, v) -> dst.getTemp().setEve((Double) v)));
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.map(OwDaily::getNightTemp, (dst, v) -> dst.getTemp().setNight((Double) v)));
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.map(OwDaily::getMornTemp, (dst, v) -> dst.getTemp().setMorn((Double) v)));
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.map(OwDaily::getMinTemp, (dst, v) -> dst.getTemp().setMin((Double) v)));
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.map(OwDaily::getMaxTemp, (dst, v) -> dst.getTemp().setMax((Double) v)));
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.map(OwDaily::getDayFeelsLike, (dst, v) -> dst.getFeelsLike().setDay((Double) v)));
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.map(OwDaily::getEveFeelsLike, (dst, v) -> dst.getFeelsLike().setEve((Double) v)));
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.map(OwDaily::getNightFeelsLike, (dst, v) -> dst.getFeelsLike().setNight((Double) v)));
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.map(OwDaily::getMornFeelsLike, (dst, v) -> dst.getFeelsLike().setMorn((Double) v)));
        typeMapDailyDtoFromDaily.addMappings(mapper -> mapper.using(weatherListDtoFromWeatherList).map(OwDaily::getWeather, OwDailyDto::setWeather));

        typeMapHourlyDtoToHourly = modelMapper.createTypeMap(OwHourlyDto.class, OwHourly.class);
        typeMapHourlyDtoToHourly.addMappings(mapper -> mapper.using(rainToDouble).map(OwHourlyDto::getRain, OwHourly::setRain1h));
        typeMapHourlyDtoToHourly.addMappings(mapper -> mapper.using(rainToDouble).map(OwHourlyDto::getSnow, OwHourly::setSnow1h));
        typeMapHourlyDtoToHourly.addMappings(mapper -> mapper.using(weatherListDtoToWeatherList).map(OwHourlyDto::getWeather, OwHourly::setWeather));

        typeMapHourlyDtoFromHourly = modelMapper.createTypeMap(OwHourly.class, OwHourlyDto.class);
        typeMapHourlyDtoFromHourly.addMappings(mapper -> mapper.using(rainFromDouble).map(OwHourly::getRain1h, OwHourlyDto::setRain));
        typeMapHourlyDtoFromHourly.addMappings(mapper -> mapper.using(rainFromDouble).map(OwHourly::getSnow1h, OwHourlyDto::setSnow));
        typeMapHourlyDtoFromHourly.addMappings(mapper -> mapper.using(weatherListDtoFromWeatherList).map(OwHourly::getWeather, OwHourlyDto::setWeather));
    }

    public OwMessage messageDtoToMessage(final OwMessageDto dto) {
        final OwMessage entity = typeMapMessageDtoToMessage.map(dto);
        return entity;
    }

    public OwMessageDto messageDtoFromMessage(final OwMessage entity) {
        final OwMessageDto dto = typeMapMessageDtoFromMessage.map(entity);
        return dto;
    }

    public OwFact factDtoToFact(final OwCurrentDto dto) {
        final OwFact entity = typeMapFactDtoToFact.map(dto);
        return entity;
    }

    public OwCurrentDto factDtoFromFact(final OwFact entity) {
        final OwCurrentDto dto = typeMapFactDtoFromFact.map(entity);
        return dto;
    }

    public List<OwWeather> weatherListDtoToWeatherList(final List<OwWeatherDto> dtoList) {
        final Type listType = new TypeToken<List<OwWeather>>() {
        }.getType();
        final List<OwWeather> entityList = modelMapper.map(dtoList, listType);
        return entityList;
    }

    public List<OwWeatherDto> weatherListDtoFromWeatherList(final List<OwWeather> entityList) {
        final Type listType = new TypeToken<List<OwWeatherDto>>() {
        }.getType();
        final List<OwWeatherDto> dtoList = modelMapper.map(entityList, listType);
        return dtoList;
    }

    public List<OwAlert> alertListDtoToAlertList(List<OwAlertDto> dtoList) {
        final Type listType = new TypeToken<List<OwAlert>>() {
        }.getType();
        final List<OwAlert> entityList = modelMapper.map(dtoList, listType);
        return entityList;
    }

    public List<OwAlertDto> alertListDtoFromAlertList(List<OwAlert> entityList) {
        final Type listType = new TypeToken<List<OwAlertDto>>() {
        }.getType();
        final List<OwAlertDto> dtoList = modelMapper.map(entityList, listType);
        return dtoList;
    }

    public List<OwDaily> dailyListDtoToDailyList(List<OwDailyDto> dtoList) {
        final Type listType = new TypeToken<List<OwDaily>>() {
        }.getType();
        final List<OwDaily> entityList = modelMapper.map(dtoList, listType);
        return entityList;
    }

    public List<OwDailyDto> dailyListDtoFromDailyList(List<OwDaily> entityList) {
        final Type listType = new TypeToken<List<OwDailyDto>>() {
        }.getType();
        final List<OwDailyDto> dtoList = modelMapper.map(entityList, listType);
        return dtoList;
    }

    public List<OwHourly> hourlyListDtoToHourlyList(List<OwHourlyDto> dtoList) {
        final Type listType = new TypeToken<List<OwHourly>>() {
        }.getType();
        final List<OwHourly> entityList = modelMapper.map(dtoList, listType);
        return entityList;
    }

    public List<OwHourlyDto> hourlyListDtoFromHourlyList(List<OwHourly> entityList) {
        final Type listType = new TypeToken<List<OwHourlyDto>>() {
        }.getType();
        final List<OwHourlyDto> dtoList = modelMapper.map(entityList, listType);
        return dtoList;
    }

    public List<OwMessage> messagesDtoToMessages(List<OwMessageDto> dto) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<OwMessageDto> messagesDtoFromMessages(List<OwMessage> entity) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<OwCurrentDto> factsDtoFromFacts(List<OwFact> entityList) {
        // FIXME make via mapper
        final List<OwCurrentDto> res = new ArrayList<>();
        for (final OwFact entity : entityList) {
            res.add(factDtoFromFact(entity));
        }
        return res;
    }

}
