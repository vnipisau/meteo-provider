/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo.provider.entity.Source;

public interface SourceRepository extends CrudRepository<Source, Long> {

    @Query(value = "select * from source as u where u.name = :name", nativeQuery = true)
    Source findByName(@Param("name") String name);

}
