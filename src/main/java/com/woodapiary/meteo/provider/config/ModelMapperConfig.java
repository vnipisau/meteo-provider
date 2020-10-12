/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.config;

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
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//TODO mapper for message info

/* TODO WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.modelmapper.internal.PropertyInfoImpl$FieldPropertyInfo (file:/mnt/hog_m2/soft/maven/.m2/repository/org/modelmapper/modelmapper/2.3.5/modelmapper-2.3.5.jar) to field java.time.LocalDateTime.date
WARNING: Please consider reporting this to the maintainers of org.modelmapper.internal.PropertyInfoImpl$FieldPropertyInfo
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
*/

@Configuration
public class ModelMapperConfig {

    Converter<String, LocalDateTime> stringToLocalDateTime = new AbstractConverter<>() {
        @Override
        protected LocalDateTime convert(String source) {
            final LocalDateTime localDate = LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
            return localDate;
        }
    };

    Converter<LocalDateTime, String> stringFromLocalDateTime = new AbstractConverter<>() {
        @Override
        protected String convert(LocalDateTime source) {
            final String localDate = source.toString() + 'Z';
            return localDate;
        }
    };

    Converter<String, LocalDate> stringToLocalDate = new AbstractConverter<>() {
        @Override
        protected LocalDate convert(String source) {
            final LocalDate localDate = LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return localDate;
        }
    };

    Converter<LocalDate, String> stringFromLocalDate = new AbstractConverter<>() {
        @Override
        protected String convert(LocalDate source) {
            final String localDate = source.toString();
            return localDate;
        }
    };

    Converter<String, LocalTime> stringToLocalTime = new AbstractConverter<>() {
        @Override
        protected LocalTime convert(String source) {
            final LocalTime localDate = LocalTime.parse(source, DateTimeFormatter.ofPattern("HH:mm"));
            return localDate;
        }
    };

    Converter<LocalTime, String> stringFromLocalTime = new AbstractConverter<>() {
        @Override
        protected String convert(LocalTime source) {
            final String localDate = source.toString();
            return localDate;
        }
    };

    Converter<Long, LocalDateTime> longToLocalDateTime = new AbstractConverter<>() {
        @Override
        protected LocalDateTime convert(Long source) {
            final LocalDateTime localDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(source), ZoneId.of("UTC"));
            return localDate;
        }
    };

    Converter<LocalDateTime, Long> longFromLocalDateTime = new AbstractConverter<>() {
        @Override
        protected Long convert(LocalDateTime source) {
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
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
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
