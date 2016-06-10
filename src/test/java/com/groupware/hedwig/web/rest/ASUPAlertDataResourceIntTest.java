package com.groupware.hedwig.web.rest;

import com.groupware.hedwig.HedwigApp;
import com.groupware.hedwig.domain.ASUPAlertData;
import com.groupware.hedwig.repository.ASUPAlertDataRepository;
import com.groupware.hedwig.service.ASUPAlertDataService;

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
 * Test class for the ASUPAlertDataResource REST controller.
 *
 * @see ASUPAlertDataResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HedwigApp.class)
@WebAppConfiguration
@IntegrationTest
public class ASUPAlertDataResourceIntTest {

    private static final String DEFAULT_ASUP_ALERT_ID = "AAAAA";
    private static final String UPDATED_ASUP_ALERT_ID = "BBBBB";
    private static final String DEFAULT_ASUP_ALERT_FILE_NAME = "AAAAA";
    private static final String UPDATED_ASUP_ALERT_FILE_NAME = "BBBBB";

    private static final String DEFAULT_ASUP_ALERT_FILE_DATA = "AAAAA";
    private static final String UPDATED_ASUP_ALERT_FILE_DATA = "BBBBB";

    @Inject
    private ASUPAlertDataRepository aSUPAlertDataRepository;

    @Inject
    private ASUPAlertDataService aSUPAlertDataService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restASUPAlertDataMockMvc;

    private ASUPAlertData aSUPAlertData;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ASUPAlertDataResource aSUPAlertDataResource = new ASUPAlertDataResource();
        ReflectionTestUtils.setField(aSUPAlertDataResource, "aSUPAlertDataService", aSUPAlertDataService);
        this.restASUPAlertDataMockMvc = MockMvcBuilders.standaloneSetup(aSUPAlertDataResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        aSUPAlertDataRepository.deleteAll();
        aSUPAlertData = new ASUPAlertData();
        aSUPAlertData.setAsup_alert_id(DEFAULT_ASUP_ALERT_ID);
        aSUPAlertData.setAsup_alert_file_name(DEFAULT_ASUP_ALERT_FILE_NAME);
        aSUPAlertData.setAsup_alert_file_data(DEFAULT_ASUP_ALERT_FILE_DATA);
    }

    @Test
    public void createASUPAlertData() throws Exception {
        int databaseSizeBeforeCreate = aSUPAlertDataRepository.findAll().size();

        // Create the ASUPAlertData

        restASUPAlertDataMockMvc.perform(post("/api/a-sup-alert-data")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(aSUPAlertData)))
                .andExpect(status().isCreated());

        // Validate the ASUPAlertData in the database
        List<ASUPAlertData> aSUPAlertData = aSUPAlertDataRepository.findAll();
        assertThat(aSUPAlertData).hasSize(databaseSizeBeforeCreate + 1);
        ASUPAlertData testASUPAlertData = aSUPAlertData.get(aSUPAlertData.size() - 1);
        assertThat(testASUPAlertData.getAsup_alert_id()).isEqualTo(DEFAULT_ASUP_ALERT_ID);
        assertThat(testASUPAlertData.getAsup_alert_file_name()).isEqualTo(DEFAULT_ASUP_ALERT_FILE_NAME);
        assertThat(testASUPAlertData.getAsup_alert_file_data()).isEqualTo(DEFAULT_ASUP_ALERT_FILE_DATA);
    }

    @Test
    public void getAllASUPAlertData() throws Exception {
        // Initialize the database
        aSUPAlertDataRepository.save(aSUPAlertData);

        // Get all the aSUPAlertData
        restASUPAlertDataMockMvc.perform(get("/api/a-sup-alert-data?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(aSUPAlertData.getId())))
                .andExpect(jsonPath("$.[*].asup_alert_id").value(hasItem(DEFAULT_ASUP_ALERT_ID.toString())))
                .andExpect(jsonPath("$.[*].asup_alert_file_name").value(hasItem(DEFAULT_ASUP_ALERT_FILE_NAME.toString())))
                .andExpect(jsonPath("$.[*].asup_alert_file_data").value(hasItem(DEFAULT_ASUP_ALERT_FILE_DATA.toString())));
    }

    @Test
    public void getASUPAlertData() throws Exception {
        // Initialize the database
        aSUPAlertDataRepository.save(aSUPAlertData);

        // Get the aSUPAlertData
        restASUPAlertDataMockMvc.perform(get("/api/a-sup-alert-data/{id}", aSUPAlertData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(aSUPAlertData.getId()))
            .andExpect(jsonPath("$.asup_alert_id").value(DEFAULT_ASUP_ALERT_ID.toString()))
            .andExpect(jsonPath("$.asup_alert_file_name").value(DEFAULT_ASUP_ALERT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.asup_alert_file_data").value(DEFAULT_ASUP_ALERT_FILE_DATA.toString()));
    }

    @Test
    public void getNonExistingASUPAlertData() throws Exception {
        // Get the aSUPAlertData
        restASUPAlertDataMockMvc.perform(get("/api/a-sup-alert-data/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateASUPAlertData() throws Exception {
        // Initialize the database
        aSUPAlertDataService.save(aSUPAlertData);

        int databaseSizeBeforeUpdate = aSUPAlertDataRepository.findAll().size();

        // Update the aSUPAlertData
        ASUPAlertData updatedASUPAlertData = new ASUPAlertData();
        updatedASUPAlertData.setId(aSUPAlertData.getId());
        updatedASUPAlertData.setAsup_alert_id(UPDATED_ASUP_ALERT_ID);
        updatedASUPAlertData.setAsup_alert_file_name(UPDATED_ASUP_ALERT_FILE_NAME);
        updatedASUPAlertData.setAsup_alert_file_data(UPDATED_ASUP_ALERT_FILE_DATA);

        restASUPAlertDataMockMvc.perform(put("/api/a-sup-alert-data")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedASUPAlertData)))
                .andExpect(status().isOk());

        // Validate the ASUPAlertData in the database
        List<ASUPAlertData> aSUPAlertData = aSUPAlertDataRepository.findAll();
        assertThat(aSUPAlertData).hasSize(databaseSizeBeforeUpdate);
        ASUPAlertData testASUPAlertData = aSUPAlertData.get(aSUPAlertData.size() - 1);
        assertThat(testASUPAlertData.getAsup_alert_id()).isEqualTo(UPDATED_ASUP_ALERT_ID);
        assertThat(testASUPAlertData.getAsup_alert_file_name()).isEqualTo(UPDATED_ASUP_ALERT_FILE_NAME);
        assertThat(testASUPAlertData.getAsup_alert_file_data()).isEqualTo(UPDATED_ASUP_ALERT_FILE_DATA);
    }

    @Test
    public void deleteASUPAlertData() throws Exception {
        // Initialize the database
        aSUPAlertDataService.save(aSUPAlertData);

        int databaseSizeBeforeDelete = aSUPAlertDataRepository.findAll().size();

        // Get the aSUPAlertData
        restASUPAlertDataMockMvc.perform(delete("/api/a-sup-alert-data/{id}", aSUPAlertData.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ASUPAlertData> aSUPAlertData = aSUPAlertDataRepository.findAll();
        assertThat(aSUPAlertData).hasSize(databaseSizeBeforeDelete - 1);
    }
}
