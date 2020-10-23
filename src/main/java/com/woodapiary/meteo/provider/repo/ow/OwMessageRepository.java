/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ow;

import org.springframework.data.repository.CrudRepository;

import com.woodapiary.meteo.provider.entity.ow.OwMessage;

public interface OwMessageRepository extends CrudRepository<OwMessage, Long> {

}
