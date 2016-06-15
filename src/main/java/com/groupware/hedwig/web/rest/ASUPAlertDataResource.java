package com.groupware.hedwig.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.groupware.hedwig.domain.ASUPAlertData;
import com.groupware.hedwig.service.ASUPAlertDataService;
import com.groupware.hedwig.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ASUPAlertData.
 */
@RestController
@RequestMapping("/api")
public class ASUPAlertDataResource {

    private final Logger log = LoggerFactory.getLogger(ASUPAlertDataResource.class);
        
    @Inject
    private ASUPAlertDataService aSUPAlertDataService;
    
    /**
     * POST  /a-sup-alert-data : Create a new aSUPAlertData.
     *
     * @param aSUPAlertData the aSUPAlertData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aSUPAlertData, or with status 400 (Bad Request) if the aSUPAlertData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/a-sup-alert-data",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ASUPAlertData> createASUPAlertData(@RequestBody ASUPAlertData aSUPAlertData) throws URISyntaxException {
        log.debug("REST request to save ASUPAlertData : {}", aSUPAlertData);
        if (aSUPAlertData.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("aSUPAlertData", "idexists", "A new aSUPAlertData cannot already have an ID")).body(null);
        }
        ASUPAlertData result = aSUPAlertDataService.save(aSUPAlertData);
        return ResponseEntity.created(new URI("/api/a-sup-alert-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("aSUPAlertData", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /a-sup-alert-data : Updates an existing aSUPAlertData.
     *
     * @param aSUPAlertData the aSUPAlertData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aSUPAlertData,
     * or with status 400 (Bad Request) if the aSUPAlertData is not valid,
     * or with status 500 (Internal Server Error) if the aSUPAlertData couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/a-sup-alert-data",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ASUPAlertData> updateASUPAlertData(@RequestBody ASUPAlertData aSUPAlertData) throws URISyntaxException {
        log.debug("REST request to update ASUPAlertData : {}", aSUPAlertData);
        if (aSUPAlertData.getId() == null) {
            return createASUPAlertData(aSUPAlertData);
        }
        ASUPAlertData result = aSUPAlertDataService.save(aSUPAlertData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("aSUPAlertData", aSUPAlertData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /a-sup-alert-data : get all the aSUPAlertData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of aSUPAlertData in body
     */
    @RequestMapping(value = "/a-sup-alert-data",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ASUPAlertData> getAllASUPAlertData() {
        log.debug("REST request to get all ASUPAlertData");
        return aSUPAlertDataService.findAll();
    }
    
    /**
     * GET  /a-sup-alert-data : get all the aSUPAlertData for a given asup id.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of aSUPAlertData in body
     */
    // TODO implement this in the service layer
    @RequestMapping(value = "/a-sup-alert-data/asup/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ASUPAlertData> getAllASUPAlertDataByASUPID(@PathVariable String id) {
        log.debug("REST request to get ASUPAlertData for ASUP id: ", id);
        List<ASUPAlertData> asupMatched = new ArrayList<ASUPAlertData>();
        List<ASUPAlertData> all = aSUPAlertDataService.findAll();
        for (ASUPAlertData alert : all) {
        	if (alert.getAsup_alert_id().equalsIgnoreCase(id)) {
        		asupMatched.add(alert);
        	}
        }
        return asupMatched;
    }

    /**
     * GET  /a-sup-alert-data/:id : get the "id" aSUPAlertData.
     *
     * @param id the id of the aSUPAlertData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aSUPAlertData, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/a-sup-alert-data/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ASUPAlertData> getASUPAlertData(@PathVariable String id) {
        log.debug("REST request to get ASUPAlertData : {}", id);
        ASUPAlertData aSUPAlertData = aSUPAlertDataService.findOne(id);
        return Optional.ofNullable(aSUPAlertData)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /a-sup-alert-data/:id : delete the "id" aSUPAlertData.
     *
     * @param id the id of the aSUPAlertData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/a-sup-alert-data/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteASUPAlertData(@PathVariable String id) {
        log.debug("REST request to delete ASUPAlertData : {}", id);
        aSUPAlertDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("aSUPAlertData", id.toString())).build();
    }

}
