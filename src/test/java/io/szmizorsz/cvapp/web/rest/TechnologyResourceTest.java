package io.szmizorsz.cvapp.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import io.szmizorsz.cvapp.Application;
import io.szmizorsz.cvapp.domain.Technology;
import io.szmizorsz.cvapp.repository.TechnologyRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TechnologyResource REST controller.
 *
 * @see TechnologyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TechnologyResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";
    

    @Inject
    private TechnologyRepository technologyRepository;

    private MockMvc restTechnologyMockMvc;

    private Technology technology;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TechnologyResource technologyResource = new TechnologyResource();
        ReflectionTestUtils.setField(technologyResource, "technologyRepository", technologyRepository);
        this.restTechnologyMockMvc = MockMvcBuilders.standaloneSetup(technologyResource).build();
    }

    @Before
    public void initTest() {
        technology = new Technology();
        technology.setName(DEFAULT_NAME);
        technology.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createTechnology() throws Exception {
        // Validate the database is empty
        assertThat(technologyRepository.findAll()).hasSize(0);

        // Create the Technology
        restTechnologyMockMvc.perform(post("/app/rest/technologys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(technology)))
                .andExpect(status().isOk());

        // Validate the Technology in the database
        List<Technology> technologys = technologyRepository.findAll();
        assertThat(technologys).hasSize(1);
        Technology testTechnology = technologys.iterator().next();
        assertThat(testTechnology.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTechnology.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllTechnologys() throws Exception {
        // Initialize the database
        technologyRepository.saveAndFlush(technology);

        // Get all the technologys
        restTechnologyMockMvc.perform(get("/app/rest/technologys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(technology.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getTechnology() throws Exception {
        // Initialize the database
        technologyRepository.saveAndFlush(technology);

        // Get the technology
        restTechnologyMockMvc.perform(get("/app/rest/technologys/{id}", technology.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(technology.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTechnology() throws Exception {
        // Get the technology
        restTechnologyMockMvc.perform(get("/app/rest/technologys/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTechnology() throws Exception {
        // Initialize the database
        technologyRepository.saveAndFlush(technology);

        // Update the technology
        technology.setName(UPDATED_NAME);
        technology.setDescription(UPDATED_DESCRIPTION);
        restTechnologyMockMvc.perform(post("/app/rest/technologys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(technology)))
                .andExpect(status().isOk());

        // Validate the Technology in the database
        List<Technology> technologys = technologyRepository.findAll();
        assertThat(technologys).hasSize(1);
        Technology testTechnology = technologys.iterator().next();
        assertThat(testTechnology.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTechnology.getDescription()).isEqualTo(UPDATED_DESCRIPTION);;
    }

    @Test
    @Transactional
    public void deleteTechnology() throws Exception {
        // Initialize the database
        technologyRepository.saveAndFlush(technology);

        // Get the technology
        restTechnologyMockMvc.perform(delete("/app/rest/technologys/{id}", technology.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Technology> technologys = technologyRepository.findAll();
        assertThat(technologys).hasSize(0);
    }
}
