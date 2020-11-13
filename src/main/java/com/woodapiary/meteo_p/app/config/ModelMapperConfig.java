/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.app.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    Converter<String, LocalDateTime> stringToLocalDateTime = new AbstractConverter<>() {
        @Override
        protected LocalDateTime convert(String source) {
            if (source == null) {
                return null;
            }
            final LocalDateTime localDate = LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
            return localDate;
        }
    };

    Converter<LocalDateTime, String> stringFromLocalDateTime = new AbstractConverter<>() {
        @Override
        protected String convert(LocalDateTime source) {
            if (source == null) {
                return null;
            }
            final String localDate = source.toString() + 'Z';
            return localDate;
        }
    };

    Converter<String, LocalDate> stringToLocalDate = new AbstractConverter<>() {
        @Override
        protected LocalDate convert(String source) {
            if (source == null) {
                return null;
            }
            final LocalDate localDate = LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return localDate;
        }
    };

    Converter<LocalDate, String> stringFromLocalDate = new AbstractConverter<>() {
        @Override
        protected String convert(LocalDate source) {
            if (source == null) {
                return null;
            }
            final String localDate = source.toString();
            return localDate;
        }
    };

    Converter<String, LocalTime> stringToLocalTime = new AbstractConverter<>() {
        @Override
        protected LocalTime convert(String source) {
            if (source == null) {
                return null;
            }
            final LocalTime localDate = LocalTime.parse(source, DateTimeFormatter.ofPattern("HH:mm"));
            return localDate;
        }
    };

    Converter<LocalTime, String> stringFromLocalTime = new AbstractConverter<>() {
        @Override
        protected String convert(LocalTime source) {
            if (source == null) {
                return null;
            }
            final String localDate = source.toString();
            return localDate;
        }
    };

    Converter<Long, LocalDateTime> longToLocalDateTime = new AbstractConverter<>() {
        @Override
        protected LocalDateTime convert(Long source) {
            if (source == null) {
                return null;
            }
            final LocalDateTime localDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(source), ZoneId.of("UTC"));
            return localDate;
        }
    };

    Converter<LocalDateTime, Long> longFromLocalDateTime = new AbstractConverter<>() {
        @Override
        protected Long convert(LocalDateTime source) {
            if (source == null) {
                return null;
            }
            final ZonedDateTime zdt = source.atZone(ZoneId.of("UTC"));
            final Long localDate = zdt.toEpochSecond();
            return localDate;
        }
    };

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);
        modelMapper.createTypeMap(String.class, LocalDateTime.class);
        modelMapper.addConverter(stringToLocalDateTime);
        modelMapper.createTypeMap(LocalDateTime.class, String.class);
        modelMapper.addConverter(stringFromLocalDateTime);
        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(stringToLocalDate);
        modelMapper.createTypeMap(LocalDate.class, String.class);
        modelMapper.addConverter(stringFromLocalDate);
        modelMapper.createTypeMap(String.class, LocalTime.class);
        modelMapper.addConverter(stringToLocalTime);
        modelMapper.createTypeMap(LocalTime.class, String.class);
        modelMapper.addConverter(stringFromLocalTime);
        //modelMapper.createTypeMap(LocalDateTime.class, Long.class);
        modelMapper.addConverter(longToLocalDateTime);
        //modelMapper.createTypeMap(Long.class, LocalDateTime.class);
        modelMapper.addConverter(longFromLocalDateTime);

        return modelMapper;
    }

}
