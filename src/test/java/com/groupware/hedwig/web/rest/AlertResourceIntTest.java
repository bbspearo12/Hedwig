package com.groupware.hedwig.web.rest;

import com.groupware.hedwig.HedwigApp;
import com.groupware.hedwig.domain.Alert;
import com.groupware.hedwig.repository.AlertRepository;
import com.groupware.hedwig.service.AlertService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the AlertResource REST controller.
 *
 * @see AlertResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HedwigApp.class)
@WebAppConfiguration
@IntegrationTest
public class AlertResourceIntTest {

    private static final String DEFAULT_GENERATED_ON = "AAAAA";
    private static final String UPDATED_GENERATED_ON = "BBBBB";
    private static final String DEFAULT_VERSION = "AAAAA";
    private static final String UPDATED_VERSION = "BBBBB";
    private static final String DEFAULT_SYSTEM_ID = "AAAAA";
    private static final String UPDATED_SYSTEM_ID = "BBBBB";
    private static final String DEFAULT_SERIAL_NUM = "AAAAA";
    private static final String UPDATED_SERIAL_NUM = "BBBBB";
    private static final String DEFAULT_HOSTNAME = "AAAAA";
    private static final String UPDATED_HOSTNAME = "BBBBB";

    private static final Integer DEFAULT_SEQUENCE = 1;
    private static final Integer UPDATED_SEQUENCE = 2;
    private static final String DEFAULT_SNMP_LOCATION = "AAAAA";
    private static final String UPDATED_SNMP_LOCATION = "BBBBB";
    private static final String DEFAULT_PARTNER_SYSTEM_ID = "AAAAA";
    private static final String UPDATED_PARTNER_SYSTEM_ID = "BBBBB";
    private static final String DEFAULT_PARTNER_SERIAL_NUM = "AAAAA";
    private static final String UPDATED_PARTNER_SERIAL_NUM = "BBBBB";
    private static final String DEFAULT_PARTNER_HOSTNAME = "AAAAA";
    private static final String UPDATED_PARTNER_HOSTNAME = "BBBBB";
    private static final String DEFAULT_BOOT_CLUSTERED = "AAAAA";
    private static final String UPDATED_BOOT_CLUSTERED = "BBBBB";

    private static final String DEFAULT_ALERTS = "AAAAA";
    private static final String UPDATED_ALERTS = "BBBBB";

    @Inject
    private AlertRepository alertRepository;

    @Inject
    private AlertService alertService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restAlertMockMvc;

    private Alert alert;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AlertResource alertResource = new AlertResource();
        ReflectionTestUtils.setField(alertResource, "alertService", alertService);
        this.restAlertMockMvc = MockMvcBuilders.standaloneSetup(alertResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        alertRepository.deleteAll();
        alert = new Alert();
        alert.setGenerated_on(DEFAULT_GENERATED_ON);
        alert.setVersion(DEFAULT_VERSION);
        alert.setSystem_id(DEFAULT_SYSTEM_ID);
        alert.setSerial_num(DEFAULT_SERIAL_NUM);
        alert.setHostname(DEFAULT_HOSTNAME);
        alert.setSequence(DEFAULT_SEQUENCE);
        alert.setSnmp_location(DEFAULT_SNMP_LOCATION);
        alert.setPartner_system_id(DEFAULT_PARTNER_SYSTEM_ID);
        alert.setPartner_serial_num(DEFAULT_PARTNER_SERIAL_NUM);
        alert.setPartner_hostname(DEFAULT_PARTNER_HOSTNAME);
        alert.setBoot_clustered(DEFAULT_BOOT_CLUSTERED);
        alert.setAlerts(DEFAULT_ALERTS);
    }

    @Test
    public void createAlert() throws Exception {
        int databaseSizeBeforeCreate = alertRepository.findAll().size();

        // Create the Alert

        restAlertMockMvc.perform(post("/api/alerts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(alert)))
                .andExpect(status().isCreated());

        // Validate the Alert in the database
        List<Alert> alerts = alertRepository.findAll();
        assertThat(alerts).hasSize(databaseSizeBeforeCreate + 1);
        Alert testAlert = alerts.get(alerts.size() - 1);
        assertThat(testAlert.getGenerated_on()).isEqualTo(DEFAULT_GENERATED_ON);
        assertThat(testAlert.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testAlert.getSystem_id()).isEqualTo(DEFAULT_SYSTEM_ID);
        assertThat(testAlert.getSerial_num()).isEqualTo(DEFAULT_SERIAL_NUM);
        assertThat(testAlert.getHostname()).isEqualTo(DEFAULT_HOSTNAME);
        assertThat(testAlert.getSequence()).isEqualTo(DEFAULT_SEQUENCE);
        assertThat(testAlert.getSnmp_location()).isEqualTo(DEFAULT_SNMP_LOCATION);
        assertThat(testAlert.getPartner_system_id()).isEqualTo(DEFAULT_PARTNER_SYSTEM_ID);
        assertThat(testAlert.getPartner_serial_num()).isEqualTo(DEFAULT_PARTNER_SERIAL_NUM);
        assertThat(testAlert.getPartner_hostname()).isEqualTo(DEFAULT_PARTNER_HOSTNAME);
        assertThat(testAlert.getBoot_clustered()).isEqualTo(DEFAULT_BOOT_CLUSTERED);
    }

    @Test
    public void getAllAlerts() throws Exception {
        // Initialize the database
        alertRepository.save(alert);

        // Get all the alerts
        restAlertMockMvc.perform(get("/api/alerts?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(alert.getId())))
                .andExpect(jsonPath("$.[*].generated_on").value(hasItem(DEFAULT_GENERATED_ON.toString())))
                .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
                .andExpect(jsonPath("$.[*].system_id").value(hasItem(DEFAULT_SYSTEM_ID.toString())))
                .andExpect(jsonPath("$.[*].serial_num").value(hasItem(DEFAULT_SERIAL_NUM.toString())))
                .andExpect(jsonPath("$.[*].hostname").value(hasItem(DEFAULT_HOSTNAME.toString())))
                .andExpect(jsonPath("$.[*].sequence").value(hasItem(DEFAULT_SEQUENCE)))
                .andExpect(jsonPath("$.[*].snmp_location").value(hasItem(DEFAULT_SNMP_LOCATION.toString())))
                .andExpect(jsonPath("$.[*].partner_system_id").value(hasItem(DEFAULT_PARTNER_SYSTEM_ID.toString())))
                .andExpect(jsonPath("$.[*].partner_serial_num").value(hasItem(DEFAULT_PARTNER_SERIAL_NUM.toString())))
                .andExpect(jsonPath("$.[*].partner_hostname").value(hasItem(DEFAULT_PARTNER_HOSTNAME.toString())))
                .andExpect(jsonPath("$.[*].boot_clustered").value(hasItem(DEFAULT_BOOT_CLUSTERED.toString())));
    }

    @Test
    public void getAlert() throws Exception {
        // Initialize the database
        alertRepository.save(alert);

        // Get the alert
        restAlertMockMvc.perform(get("/api/alerts/{id}", alert.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(alert.getId()))
            .andExpect(jsonPath("$.generated_on").value(DEFAULT_GENERATED_ON.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.system_id").value(DEFAULT_SYSTEM_ID.toString()))
            .andExpect(jsonPath("$.serial_num").value(DEFAULT_SERIAL_NUM.toString()))
            .andExpect(jsonPath("$.hostname").value(DEFAULT_HOSTNAME.toString()))
            .andExpect(jsonPath("$.sequence").value(DEFAULT_SEQUENCE))
            .andExpect(jsonPath("$.snmp_location").value(DEFAULT_SNMP_LOCATION.toString()))
            .andExpect(jsonPath("$.partner_system_id").value(DEFAULT_PARTNER_SYSTEM_ID.toString()))
            .andExpect(jsonPath("$.partner_serial_num").value(DEFAULT_PARTNER_SERIAL_NUM.toString()))
            .andExpect(jsonPath("$.partner_hostname").value(DEFAULT_PARTNER_HOSTNAME.toString()))
            .andExpect(jsonPath("$.boot_clustered").value(DEFAULT_BOOT_CLUSTERED.toString()));
    }

    @Test
    public void getNonExistingAlert() throws Exception {
        // Get the alert
        restAlertMockMvc.perform(get("/api/alerts/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateAlert() throws Exception {
        // Initialize the database
        alertService.save(alert);

        int databaseSizeBeforeUpdate = alertRepository.findAll().size();

        // Update the alert
        Alert updatedAlert = new Alert();
        updatedAlert.setId(alert.getId());
        updatedAlert.setGenerated_on(UPDATED_GENERATED_ON);
        updatedAlert.setVersion(UPDATED_VERSION);
        updatedAlert.setSystem_id(UPDATED_SYSTEM_ID);
        updatedAlert.setSerial_num(UPDATED_SERIAL_NUM);
        updatedAlert.setHostname(UPDATED_HOSTNAME);
        updatedAlert.setSequence(UPDATED_SEQUENCE);
        updatedAlert.setSnmp_location(UPDATED_SNMP_LOCATION);
        updatedAlert.setPartner_system_id(UPDATED_PARTNER_SYSTEM_ID);
        updatedAlert.setPartner_serial_num(UPDATED_PARTNER_SERIAL_NUM);
        updatedAlert.setPartner_hostname(UPDATED_PARTNER_HOSTNAME);
        updatedAlert.setBoot_clustered(UPDATED_BOOT_CLUSTERED);
        updatedAlert.setAlerts(UPDATED_ALERTS);

        restAlertMockMvc.perform(put("/api/alerts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedAlert)))
                .andExpect(status().isOk());

        // Validate the Alert in the database
        List<Alert> alerts = alertRepository.findAll();
        assertThat(alerts).hasSize(databaseSizeBeforeUpdate);
        Alert testAlert = alerts.get(alerts.size() - 1);
        assertThat(testAlert.getGenerated_on()).isEqualTo(UPDATED_GENERATED_ON);
        assertThat(testAlert.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testAlert.getSystem_id()).isEqualTo(UPDATED_SYSTEM_ID);
        assertThat(testAlert.getSerial_num()).isEqualTo(UPDATED_SERIAL_NUM);
        assertThat(testAlert.getHostname()).isEqualTo(UPDATED_HOSTNAME);
        assertThat(testAlert.getSequence()).isEqualTo(UPDATED_SEQUENCE);
        assertThat(testAlert.getSnmp_location()).isEqualTo(UPDATED_SNMP_LOCATION);
        assertThat(testAlert.getPartner_system_id()).isEqualTo(UPDATED_PARTNER_SYSTEM_ID);
        assertThat(testAlert.getPartner_serial_num()).isEqualTo(UPDATED_PARTNER_SERIAL_NUM);
        assertThat(testAlert.getPartner_hostname()).isEqualTo(UPDATED_PARTNER_HOSTNAME);
        assertThat(testAlert.getBoot_clustered()).isEqualTo(UPDATED_BOOT_CLUSTERED);
    }

    @Test
    public void deleteAlert() throws Exception {
        // Initialize the database
        alertService.save(alert);

        int databaseSizeBeforeDelete = alertRepository.findAll().size();

        // Get the alert
        restAlertMockMvc.perform(delete("/api/alerts/{id}", alert.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Alert> alerts = alertRepository.findAll();
        assertThat(alerts).hasSize(databaseSizeBeforeDelete - 1);
    }
}
