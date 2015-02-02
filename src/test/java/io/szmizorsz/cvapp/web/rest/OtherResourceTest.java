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
import io.szmizorsz.cvapp.domain.Other;
import io.szmizorsz.cvapp.repositoryMongo.OtherRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OtherResource REST controller.
 *
 * @see OtherResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class OtherResourceTest {

    private static final String DEFAULT_CATEGORY_EN = "SAMPLE_TEXT";
    private static final String UPDATED_CATEGORY_EN = "UPDATED_TEXT";
    
    private static final String DEFAULT_CATEGORY_HU = "SAMPLE_TEXT";
    private static final String UPDATED_CATEGORY_HU = "UPDATED_TEXT";
    
    private static final String DEFAULT_DESCRIPTION_EN = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION_EN = "UPDATED_TEXT";
    
    private static final String DEFAULT_DESCRIPTION_HU = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION_HU = "UPDATED_TEXT";
    

    @Inject
    private OtherRepository otherRepository;

    private MockMvc restOtherMockMvc;

    private Other other;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OtherResource otherResource = new OtherResource();
        ReflectionTestUtils.setField(otherResource, "otherRepository", otherRepository);
        this.restOtherMockMvc = MockMvcBuilders.standaloneSetup(otherResource).build();
    }

    @Before
    public void initTest() {
        otherRepository.deleteAll();
        other = new Other();
        other.setCategoryEn(DEFAULT_CATEGORY_EN);
        other.setCategoryHu(DEFAULT_CATEGORY_HU);
        other.setDescriptionEn(DEFAULT_DESCRIPTION_EN);
        other.setDescriptionHu(DEFAULT_DESCRIPTION_HU);
    }

    @Test
    public void createOther() throws Exception {
        // Validate the database is empty
        assertThat(otherRepository.findAll()).hasSize(0);

        // Create the Other
        restOtherMockMvc.perform(post("/app/rest/others")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(other)))
                .andExpect(status().isOk());

        // Validate the Other in the database
        List<Other> others = otherRepository.findAll();
        assertThat(others).hasSize(1);
        Other testOther = others.iterator().next();
        assertThat(testOther.getCategoryEn()).isEqualTo(DEFAULT_CATEGORY_EN);
        assertThat(testOther.getCategoryHu()).isEqualTo(DEFAULT_CATEGORY_HU);
        assertThat(testOther.getDescriptionEn()).isEqualTo(DEFAULT_DESCRIPTION_EN);
        assertThat(testOther.getDescriptionHu()).isEqualTo(DEFAULT_DESCRIPTION_HU);
    }

    @Test
    public void getAllOthers() throws Exception {
        // Initialize the database
        otherRepository.save(other);

        // Get all the others
        restOtherMockMvc.perform(get("/app/rest/others"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(other.getId()))
                .andExpect(jsonPath("$.[0].categoryEn").value(DEFAULT_CATEGORY_EN.toString()))
                .andExpect(jsonPath("$.[0].categoryHu").value(DEFAULT_CATEGORY_HU.toString()))
                .andExpect(jsonPath("$.[0].descriptionEn").value(DEFAULT_DESCRIPTION_EN.toString()))
                .andExpect(jsonPath("$.[0].descriptionHu").value(DEFAULT_DESCRIPTION_HU.toString()));
    }

    @Test
    public void getOther() throws Exception {
        // Initialize the database
        otherRepository.save(other);

        // Get the other
        restOtherMockMvc.perform(get("/app/rest/others/{id}", other.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(other.getId()))
            .andExpect(jsonPath("$.categoryEn").value(DEFAULT_CATEGORY_EN.toString()))
            .andExpect(jsonPath("$.categoryHu").value(DEFAULT_CATEGORY_HU.toString()))
            .andExpect(jsonPath("$.descriptionEn").value(DEFAULT_DESCRIPTION_EN.toString()))
            .andExpect(jsonPath("$.descriptionHu").value(DEFAULT_DESCRIPTION_HU.toString()));
    }

    @Test
    public void getNonExistingOther() throws Exception {
        // Get the other
        restOtherMockMvc.perform(get("/app/rest/others/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateOther() throws Exception {
        // Initialize the database
        otherRepository.save(other);

        // Update the other
        other.setCategoryEn(UPDATED_CATEGORY_EN);
        other.setCategoryHu(UPDATED_CATEGORY_HU);
        other.setDescriptionEn(UPDATED_DESCRIPTION_EN);
        other.setDescriptionHu(UPDATED_DESCRIPTION_HU);
        restOtherMockMvc.perform(post("/app/rest/others")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(other)))
                .andExpect(status().isOk());

        // Validate the Other in the database
        List<Other> others = otherRepository.findAll();
        assertThat(others).hasSize(1);
        Other testOther = others.iterator().next();
        assertThat(testOther.getCategoryEn()).isEqualTo(UPDATED_CATEGORY_EN);
        assertThat(testOther.getCategoryHu()).isEqualTo(UPDATED_CATEGORY_HU);
        assertThat(testOther.getDescriptionEn()).isEqualTo(UPDATED_DESCRIPTION_EN);
        assertThat(testOther.getDescriptionHu()).isEqualTo(UPDATED_DESCRIPTION_HU);;
    }

    @Test
    public void deleteOther() throws Exception {
        // Initialize the database
        otherRepository.save(other);

        // Get the other
        restOtherMockMvc.perform(delete("/app/rest/others/{id}", other.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Other> others = otherRepository.findAll();
        assertThat(others).hasSize(0);
    }
}
