/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.repo;

import org.springframework.data.repository.CrudRepository;

import com.woodapiary.meteo.ya.entity.Forecast;

public interface ForecastRepository extends CrudRepository<Forecast, Long> {

}
