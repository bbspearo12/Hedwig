package com.groupware.hedwig.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.groupware.hedwig.domain.Alert;
import com.groupware.hedwig.service.AlertService;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Alert.
 */
@RestController
@RequestMapping("/api")
public class AlertResource {

    private final Logger log = LoggerFactory.getLogger(AlertResource.class);
        
    @Inject
    private AlertService alertService;
    
    /**
     * POST  /alerts : Create a new alert.
     *
     * @param alert the alert to create
     * @return the ResponseEntity with status 201 (Created) and with body the new alert, or with status 400 (Bad Request) if the alert has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/alerts",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) throws URISyntaxException {
        log.debug("REST request to save Alert : {}", alert);
        if (alert.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("alert", "idexists", "A new alert cannot already have an ID")).body(null);
        }
        Alert result = alertService.save(alert);
        return ResponseEntity.created(new URI("/api/alerts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("alert", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /alerts : Updates an existing alert.
     *
     * @param alert the alert to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated alert,
     * or with status 400 (Bad Request) if the alert is not valid,
     * or with status 500 (Internal Server Error) if the alert couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/alerts",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Alert> updateAlert(@RequestBody Alert alert) throws URISyntaxException {
        log.debug("REST request to update Alert : {}", alert);
        if (alert.getId() == null) {
            return createAlert(alert);
        }
        Alert result = alertService.save(alert);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("alert", alert.getId().toString()))
            .body(result);
    }

    /**
     * GET  /alerts : get all the alerts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of alerts in body
     */
    @RequestMapping(value = "/alerts",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Alert> getAllAlerts() {
        log.debug("REST request to get all Alerts");
        return alertService.findAll();
    }

    /**
     * GET  /alerts/:id : get the "id" alert.
     *
     * @param id the id of the alert to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the alert, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/alerts/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Alert> getAlert(@PathVariable String id) {
        log.debug("REST request to get Alert : {}", id);
        Alert alert = alertService.findOne(id);
        return Optional.ofNullable(alert)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /alerts/:id : delete the "id" alert.
     *
     * @param id the id of the alert to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/alerts/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteAlert(@PathVariable String id) {
        log.debug("REST request to delete Alert : {}", id);
        alertService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("alert", id.toString())).build();
    }

}
