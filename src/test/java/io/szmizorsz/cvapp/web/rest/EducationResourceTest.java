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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import io.szmizorsz.cvapp.Application;
import io.szmizorsz.cvapp.domain.Education;
import io.szmizorsz.cvapp.repositoryMongo.EducationRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EducationResource REST controller.
 *
 * @see EducationResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EducationResourceTest {

    private static final String DEFAULT_PERIOD_EN = "SAMPLE_TEXT";
    private static final String UPDATED_PERIOD_EN = "UPDATED_TEXT";
    
    private static final String DEFAULT_PERIOD_HU = "SAMPLE_TEXT";
    private static final String UPDATED_PERIOD_HU = "UPDATED_TEXT";
    
    private static final String DEFAULT_DESCRIPTION_EN = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION_EN = "UPDATED_TEXT";
    
    private static final String DEFAULT_DESCRIPTION_HU = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION_HU = "UPDATED_TEXT";
    

    @Inject
    private EducationRepository educationRepository;

    private MockMvc restEducationMockMvc;

    private Education education;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EducationResource educationResource = new EducationResource();
        ReflectionTestUtils.setField(educationResource, "educationRepository", educationRepository);
        this.restEducationMockMvc = MockMvcBuilders.standaloneSetup(educationResource).build();
    }

    @Before
    public void initTest() {
        educationRepository.deleteAll();
        education = new Education();
        education.setPeriodEn(DEFAULT_PERIOD_EN);
        education.setPeriodHu(DEFAULT_PERIOD_HU);
        education.setDescriptionEn(DEFAULT_DESCRIPTION_EN);
        education.setDescriptionHu(DEFAULT_DESCRIPTION_HU);
    }

    @Test
    public void createEducation() throws Exception {
        // Validate the database is empty
        assertThat(educationRepository.findAll()).hasSize(0);

        // Create the Education
        restEducationMockMvc.perform(post("/app/rest/educations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(education)))
                .andExpect(status().isOk());

        // Validate the Education in the database
        List<Education> educations = educationRepository.findAll();
        assertThat(educations).hasSize(1);
        Education testEducation = educations.iterator().next();
        assertThat(testEducation.getPeriodEn()).isEqualTo(DEFAULT_PERIOD_EN);
        assertThat(testEducation.getPeriodHu()).isEqualTo(DEFAULT_PERIOD_HU);
        assertThat(testEducation.getDescriptionEn()).isEqualTo(DEFAULT_DESCRIPTION_EN);
        assertThat(testEducation.getDescriptionHu()).isEqualTo(DEFAULT_DESCRIPTION_HU);
    }

    @Test
    public void getAllEducations() throws Exception {
        // Initialize the database
        educationRepository.save(education);

        // Get all the educations
        restEducationMockMvc.perform(get("/app/rest/educations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(education.getId()))
                .andExpect(jsonPath("$.[0].periodEn").value(DEFAULT_PERIOD_EN.toString()))
                .andExpect(jsonPath("$.[0].periodHu").value(DEFAULT_PERIOD_HU.toString()))
                .andExpect(jsonPath("$.[0].descriptionEn").value(DEFAULT_DESCRIPTION_EN.toString()))
                .andExpect(jsonPath("$.[0].descriptionHu").value(DEFAULT_DESCRIPTION_HU.toString()));
    }

    @Test
    public void getEducation() throws Exception {
        // Initialize the database
        educationRepository.save(education);

        // Get the education
        restEducationMockMvc.perform(get("/app/rest/educations/{id}", education.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(education.getId()))
            .andExpect(jsonPath("$.periodEn").value(DEFAULT_PERIOD_EN.toString()))
            .andExpect(jsonPath("$.periodHu").value(DEFAULT_PERIOD_HU.toString()))
            .andExpect(jsonPath("$.descriptionEn").value(DEFAULT_DESCRIPTION_EN.toString()))
            .andExpect(jsonPath("$.descriptionHu").value(DEFAULT_DESCRIPTION_HU.toString()));
    }

    @Test
    public void getNonExistingEducation() throws Exception {
        // Get the education
        restEducationMockMvc.perform(get("/app/rest/educations/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateEducation() throws Exception {
        // Initialize the database
        educationRepository.save(education);

        // Update the education
        education.setPeriodEn(UPDATED_PERIOD_EN);
        education.setPeriodHu(UPDATED_PERIOD_HU);
        education.setDescriptionEn(UPDATED_DESCRIPTION_EN);
        education.setDescriptionHu(UPDATED_DESCRIPTION_HU);
        restEducationMockMvc.perform(post("/app/rest/educations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(education)))
                .andExpect(status().isOk());

        // Validate the Education in the database
        List<Education> educations = educationRepository.findAll();
        assertThat(educations).hasSize(1);
        Education testEducation = educations.iterator().next();
        assertThat(testEducation.getPeriodEn()).isEqualTo(UPDATED_PERIOD_EN);
        assertThat(testEducation.getPeriodHu()).isEqualTo(UPDATED_PERIOD_HU);
        assertThat(testEducation.getDescriptionEn()).isEqualTo(UPDATED_DESCRIPTION_EN);
        assertThat(testEducation.getDescriptionHu()).isEqualTo(UPDATED_DESCRIPTION_HU);;
    }

    @Test
    public void deleteEducation() throws Exception {
        // Initialize the database
        educationRepository.save(education);

        // Get the education
        restEducationMockMvc.perform(delete("/app/rest/educations/{id}", education.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Education> educations = educationRepository.findAll();
        assertThat(educations).hasSize(0);
    }
}
