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
import io.szmizorsz.cvapp.domain.Knowledge;
import io.szmizorsz.cvapp.repositoryMongo.KnowledgeRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the KnowledgeResource REST controller.
 *
 * @see KnowledgeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class KnowledgeResourceTest {

    private static final String DEFAULT_CATEGORY_EN = "SAMPLE_TEXT";
    private static final String UPDATED_CATEGORY_EN = "UPDATED_TEXT";
    
    private static final String DEFAULT_CATEGORY_HU = "SAMPLE_TEXT";
    private static final String UPDATED_CATEGORY_HU = "UPDATED_TEXT";
    
    private static final String DEFAULT_DETAILS_EN = "SAMPLE_TEXT";
    private static final String UPDATED_DETAILS_EN = "UPDATED_TEXT";
    
    private static final String DEFAULT_DETAILS_HU = "SAMPLE_TEXT";
    private static final String UPDATED_DETAILS_HU = "UPDATED_TEXT";
    

    @Inject
    private KnowledgeRepository knowledgeRepository;

    private MockMvc restKnowledgeMockMvc;

    private Knowledge knowledge;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        KnowledgeResource knowledgeResource = new KnowledgeResource();
        ReflectionTestUtils.setField(knowledgeResource, "knowledgeRepository", knowledgeRepository);
        this.restKnowledgeMockMvc = MockMvcBuilders.standaloneSetup(knowledgeResource).build();
    }

    @Before
    public void initTest() {
        knowledgeRepository.deleteAll();
        knowledge = new Knowledge();
        knowledge.setCategoryEn(DEFAULT_CATEGORY_EN);
        knowledge.setCategoryHu(DEFAULT_CATEGORY_HU);
        knowledge.setDetailsEn(DEFAULT_DETAILS_EN);
        knowledge.setDetailsHu(DEFAULT_DETAILS_HU);
    }

    @Test
    public void createKnowledge() throws Exception {
        // Validate the database is empty
        assertThat(knowledgeRepository.findAll()).hasSize(0);

        // Create the Knowledge
        restKnowledgeMockMvc.perform(post("/app/rest/knowledges")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(knowledge)))
                .andExpect(status().isOk());

        // Validate the Knowledge in the database
        List<Knowledge> knowledges = knowledgeRepository.findAll();
        assertThat(knowledges).hasSize(1);
        Knowledge testKnowledge = knowledges.iterator().next();
        assertThat(testKnowledge.getCategoryEn()).isEqualTo(DEFAULT_CATEGORY_EN);
        assertThat(testKnowledge.getCategoryHu()).isEqualTo(DEFAULT_CATEGORY_HU);
        assertThat(testKnowledge.getDetailsEn()).isEqualTo(DEFAULT_DETAILS_EN);
        assertThat(testKnowledge.getDetailsHu()).isEqualTo(DEFAULT_DETAILS_HU);
    }

    @Test
    public void getAllKnowledges() throws Exception {
        // Initialize the database
        knowledgeRepository.save(knowledge);

        // Get all the knowledges
        restKnowledgeMockMvc.perform(get("/app/rest/knowledges"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(knowledge.getId()))
                .andExpect(jsonPath("$.[0].categoryEn").value(DEFAULT_CATEGORY_EN.toString()))
                .andExpect(jsonPath("$.[0].categoryHu").value(DEFAULT_CATEGORY_HU.toString()))
                .andExpect(jsonPath("$.[0].detailsEn").value(DEFAULT_DETAILS_EN.toString()))
                .andExpect(jsonPath("$.[0].detailsHu").value(DEFAULT_DETAILS_HU.toString()));
    }

    @Test
    public void getKnowledge() throws Exception {
        // Initialize the database
        knowledgeRepository.save(knowledge);

        // Get the knowledge
        restKnowledgeMockMvc.perform(get("/app/rest/knowledges/{id}", knowledge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(knowledge.getId()))
            .andExpect(jsonPath("$.categoryEn").value(DEFAULT_CATEGORY_EN.toString()))
            .andExpect(jsonPath("$.categoryHu").value(DEFAULT_CATEGORY_HU.toString()))
            .andExpect(jsonPath("$.detailsEn").value(DEFAULT_DETAILS_EN.toString()))
            .andExpect(jsonPath("$.detailsHu").value(DEFAULT_DETAILS_HU.toString()));
    }

    @Test
    public void getNonExistingKnowledge() throws Exception {
        // Get the knowledge
        restKnowledgeMockMvc.perform(get("/app/rest/knowledges/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateKnowledge() throws Exception {
        // Initialize the database
        knowledgeRepository.save(knowledge);

        // Update the knowledge
        knowledge.setCategoryEn(UPDATED_CATEGORY_EN);
        knowledge.setCategoryHu(UPDATED_CATEGORY_HU);
        knowledge.setDetailsEn(UPDATED_DETAILS_EN);
        knowledge.setDetailsHu(UPDATED_DETAILS_HU);
        restKnowledgeMockMvc.perform(post("/app/rest/knowledges")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(knowledge)))
                .andExpect(status().isOk());

        // Validate the Knowledge in the database
        List<Knowledge> knowledges = knowledgeRepository.findAll();
        assertThat(knowledges).hasSize(1);
        Knowledge testKnowledge = knowledges.iterator().next();
        assertThat(testKnowledge.getCategoryEn()).isEqualTo(UPDATED_CATEGORY_EN);
        assertThat(testKnowledge.getCategoryHu()).isEqualTo(UPDATED_CATEGORY_HU);
        assertThat(testKnowledge.getDetailsEn()).isEqualTo(UPDATED_DETAILS_EN);
        assertThat(testKnowledge.getDetailsHu()).isEqualTo(UPDATED_DETAILS_HU);;
    }

    @Test
    public void deleteKnowledge() throws Exception {
        // Initialize the database
        knowledgeRepository.save(knowledge);

        // Get the knowledge
        restKnowledgeMockMvc.perform(delete("/app/rest/knowledges/{id}", knowledge.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Knowledge> knowledges = knowledgeRepository.findAll();
        assertThat(knowledges).hasSize(0);
    }
}
