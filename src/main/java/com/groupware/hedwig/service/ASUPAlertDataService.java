package com.groupware.hedwig.service;

import com.groupware.hedwig.domain.ASUPAlertData;
import com.groupware.hedwig.repository.ASUPAlertDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing ASUPAlertData.
 */
@Service
public class ASUPAlertDataService {

	private final Logger log = LoggerFactory.getLogger(ASUPAlertDataService.class);

	@Inject
	private ASUPAlertDataRepository aSUPAlertDataRepository;
	@Inject
	private MongoOperations mongoOperation;

	/**
	 * Save a aSUPAlertData.
	 * 
	 * @param aSUPAlertData the entity to save
	 * @return the persisted entity
	 */
	public ASUPAlertData save(ASUPAlertData aSUPAlertData) {
		log.debug("Request to save ASUPAlertData : {}", aSUPAlertData);
		ASUPAlertData result = aSUPAlertDataRepository.save(aSUPAlertData);
		return result;
	}

	/**
	 *  Get all the aSUPAlertData.
	 *  
	 *  @return the list of entities
	 */
	public List<ASUPAlertData> findAll() {
		//log.debug("Request to get all ASUPAlertData");
		List<ASUPAlertData> result = aSUPAlertDataRepository.findAll();
		return result;
	}

	/**
	 *  Get all the aSUPAlertData for a particular ASUP.
	 *  
	 *  @return the list of entities
	 */
	public List<ASUPAlertData> findAllByASUP(String id) {
		log.debug("Request to get all ASUPAlertData for asup: {}", id);
		BasicQuery q = new BasicQuery("{ asup_alert_id: \""+ id +"\" }");
		List<ASUPAlertData> result = mongoOperation.find(q, ASUPAlertData.class);
		return result;
	}

	/**
	 *  Get one aSUPAlertData by id.
	 *
	 *  @param id the id of the entity
	 *  @return the entity
	 */
	public ASUPAlertData findOne(String id) {
		log.debug("Request to get ASUPAlertData : {}", id);
		ASUPAlertData aSUPAlertData = aSUPAlertDataRepository.findOne(id);
		return aSUPAlertData;
	}

	/**
	 *  Delete the  aSUPAlertData by id.
	 *  
	 *  @param id the id of the entity
	 */
	public void delete(String id) {
		log.debug("Request to delete ASUPAlertData : {}", id);
		aSUPAlertDataRepository.delete(id);
	}
}
