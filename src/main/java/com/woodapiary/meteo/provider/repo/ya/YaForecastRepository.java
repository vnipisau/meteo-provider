/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ya;

import org.springframework.data.repository.CrudRepository;

import com.woodapiary.meteo.provider.entity.ya.YaForecast;

public interface YaForecastRepository extends CrudRepository<YaForecast, Long> {

}