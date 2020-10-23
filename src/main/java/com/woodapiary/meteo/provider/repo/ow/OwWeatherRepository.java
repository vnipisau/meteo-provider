/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ow;

import org.springframework.data.repository.CrudRepository;

import com.woodapiary.meteo.provider.entity.ow.OwWeather;

public interface OwWeatherRepository extends CrudRepository<OwWeather, Long> {

}
