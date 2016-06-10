package com.groupware.hedwig.repository;

import com.groupware.hedwig.domain.ASUPAlertData;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the ASUPAlertData entity.
 */
public interface ASUPAlertDataRepository extends MongoRepository<ASUPAlertData,String> {

}
