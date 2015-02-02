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
import io.szmizorsz.cvapp.domain.Language;
import io.szmizorsz.cvapp.repositoryMongo.LanguageRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LanguageResource REST controller.
 *
 * @see LanguageResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class LanguageResourceTest {

    private static final String DEFAULT_NAME_EN = "SAMPLE_TEXT";
    private static final String UPDATED_NAME_EN = "UPDATED_TEXT";
    
    private static final String DEFAULT_NAME_HU = "SAMPLE_TEXT";
    private static final String UPDATED_NAME_HU = "UPDATED_TEXT";
    
    private static final String DEFAULT_LEVEL_EN = "SAMPLE_TEXT";
    private static final String UPDATED_LEVEL_EN = "UPDATED_TEXT";
    
    private static final String DEFAULT_LEVEL_HU = "SAMPLE_TEXT";
    private static final String UPDATED_LEVEL_HU = "UPDATED_TEXT";
    

    @Inject
    private LanguageRepository languageRepository;

    private MockMvc restLanguageMockMvc;

    private Language language;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LanguageResource languageResource = new LanguageResource();
        ReflectionTestUtils.setField(languageResource, "languageRepository", languageRepository);
        this.restLanguageMockMvc = MockMvcBuilders.standaloneSetup(languageResource).build();
    }

    @Before
    public void initTest() {
        languageRepository.deleteAll();
        language = new Language();
        language.setNameEn(DEFAULT_NAME_EN);
        language.setNameHu(DEFAULT_NAME_HU);
        language.setLevelEn(DEFAULT_LEVEL_EN);
        language.setLevelHu(DEFAULT_LEVEL_HU);
    }

    @Test
    public void createLanguage() throws Exception {
        // Validate the database is empty
        assertThat(languageRepository.findAll()).hasSize(0);

        // Create the Language
        restLanguageMockMvc.perform(post("/app/rest/languages")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(language)))
                .andExpect(status().isOk());

        // Validate the Language in the database
        List<Language> languages = languageRepository.findAll();
        assertThat(languages).hasSize(1);
        Language testLanguage = languages.iterator().next();
        assertThat(testLanguage.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testLanguage.getNameHu()).isEqualTo(DEFAULT_NAME_HU);
        assertThat(testLanguage.getLevelEn()).isEqualTo(DEFAULT_LEVEL_EN);
        assertThat(testLanguage.getLevelHu()).isEqualTo(DEFAULT_LEVEL_HU);
    }

    @Test
    public void getAllLanguages() throws Exception {
        // Initialize the database
        languageRepository.save(language);

        // Get all the languages
        restLanguageMockMvc.perform(get("/app/rest/languages"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(language.getId()))
                .andExpect(jsonPath("$.[0].nameEn").value(DEFAULT_NAME_EN.toString()))
                .andExpect(jsonPath("$.[0].nameHu").value(DEFAULT_NAME_HU.toString()))
                .andExpect(jsonPath("$.[0].levelEn").value(DEFAULT_LEVEL_EN.toString()))
                .andExpect(jsonPath("$.[0].levelHu").value(DEFAULT_LEVEL_HU.toString()));
    }

    @Test
    public void getLanguage() throws Exception {
        // Initialize the database
        languageRepository.save(language);

        // Get the language
        restLanguageMockMvc.perform(get("/app/rest/languages/{id}", language.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(language.getId()))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN.toString()))
            .andExpect(jsonPath("$.nameHu").value(DEFAULT_NAME_HU.toString()))
            .andExpect(jsonPath("$.levelEn").value(DEFAULT_LEVEL_EN.toString()))
            .andExpect(jsonPath("$.levelHu").value(DEFAULT_LEVEL_HU.toString()));
    }

    @Test
    public void getNonExistingLanguage() throws Exception {
        // Get the language
        restLanguageMockMvc.perform(get("/app/rest/languages/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateLanguage() throws Exception {
        // Initialize the database
        languageRepository.save(language);

        // Update the language
        language.setNameEn(UPDATED_NAME_EN);
        language.setNameHu(UPDATED_NAME_HU);
        language.setLevelEn(UPDATED_LEVEL_EN);
        language.setLevelHu(UPDATED_LEVEL_HU);
        restLanguageMockMvc.perform(post("/app/rest/languages")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(language)))
                .andExpect(status().isOk());

        // Validate the Language in the database
        List<Language> languages = languageRepository.findAll();
        assertThat(languages).hasSize(1);
        Language testLanguage = languages.iterator().next();
        assertThat(testLanguage.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testLanguage.getNameHu()).isEqualTo(UPDATED_NAME_HU);
        assertThat(testLanguage.getLevelEn()).isEqualTo(UPDATED_LEVEL_EN);
        assertThat(testLanguage.getLevelHu()).isEqualTo(UPDATED_LEVEL_HU);;
    }

    @Test
    public void deleteLanguage() throws Exception {
        // Initialize the database
        languageRepository.save(language);

        // Get the language
        restLanguageMockMvc.perform(delete("/app/rest/languages/{id}", language.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Language> languages = languageRepository.findAll();
        assertThat(languages).hasSize(0);
    }
}
