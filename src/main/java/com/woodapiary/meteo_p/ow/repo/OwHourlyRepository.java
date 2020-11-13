/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.repo;

import org.springframework.data.repository.CrudRepository;

import com.woodapiary.meteo_p.ow.entities.OwHourly;

public interface OwHourlyRepository extends CrudRepository<OwHourly, Long> {

}
