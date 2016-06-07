package com.groupware.hedwig.repository;

import com.groupware.hedwig.domain.Alert;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Alert entity.
 */
public interface AlertRepository extends MongoRepository<Alert,String> {

}
