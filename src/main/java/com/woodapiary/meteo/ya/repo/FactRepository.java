/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.repo;

import org.springframework.data.repository.CrudRepository;

import com.woodapiary.meteo.ya.entity.Fact;

public interface FactRepository extends CrudRepository<Fact, Long> {

}
