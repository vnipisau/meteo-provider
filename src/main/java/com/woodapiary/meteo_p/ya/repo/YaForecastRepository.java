/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya.repo;

import org.springframework.data.repository.CrudRepository;

import com.woodapiary.meteo_p.ya.entities.YaForecast;

public interface YaForecastRepository extends CrudRepository<YaForecast, Long> {

}
