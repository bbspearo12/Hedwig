package com.groupware.hedwig.service;

import com.groupware.hedwig.domain.ASUPAlertData;
import com.groupware.hedwig.domain.Alert;
import com.groupware.hedwig.repository.AlertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Implementation for managing Alert.
 */
@Service
public class AlertService {

    private final Logger log = LoggerFactory.getLogger(AlertService.class);
    
    @Inject
    private AlertRepository alertRepository;
    @Inject
	private MongoOperations mongoOperation;
    
    /**
     * Save a alert.
     * 
     * @param alert the entity to save
     * @return the persisted entity
     */
    public Alert save(Alert alert) {
        log.debug("Request to save Alert : {}", alert);
        Alert result = alertRepository.save(alert);
        return result;
    }

    /**
     *  Get all the alerts.
     *  
     *  @return the list of entities
     */
    public List<Alert> findAll() {
        log.debug("Request to get all Alerts");
        List<Alert> result = alertRepository.findAll();
        return result;
    }
    
    /**
     *  Get latest 1000 alerts, sorted.
     *  
     *  @return the list of entities
     */
    public List<Alert> findLatest() {
        log.debug("Request to get all Alerts");
        Query q = new Query().with(new Sort(Sort.Direction.DESC, "_id")).limit(1000);
        List<Alert> result = mongoOperation.find(q, Alert.class);
        List<Alert> finalList = new ArrayList<Alert>();
        for (Alert alert: result) {
        	if (alert.getAsup_type() != null && (
        			alert.getAsup_type().contains(Alert.asupTypeUser) ||
        			alert.getAsup_type().contains(Alert.asupTypeWeekly))) {
        		finalList.add(alert);
        	}
        }
        return finalList;
    }

    /**
     *  Get one alert by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public Alert findOne(String id) {
        log.debug("Request to get Alert : {}", id);
        Alert alert = alertRepository.findOne(id);
        return alert;
    }

    /**
     *  Delete the  alert by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Alert : {}", id);
        alertRepository.delete(id);
    }
}
