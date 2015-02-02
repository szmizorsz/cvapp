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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.joda.time.LocalDate;

import java.util.List;

import io.szmizorsz.cvapp.Application;
import io.szmizorsz.cvapp.domain.Company;
import io.szmizorsz.cvapp.domain.Project;
import io.szmizorsz.cvapp.repository.CompanyRepository;
import io.szmizorsz.cvapp.repository.ProjectRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjectResource REST controller.
 *
 * @see ProjectResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ProjectResourceT {

	private static final String FIRST_COMPANY_NAME = "FIRST_COMPANY";
    private static final String SECOND_COMPANY_NAME = "SECOND_COMPANY";
    private static final String FIRST_PROJECT_NAME = "FIRST_PROJECT";
    private static final String SECOND_PROJECT_NAME = "SECOND_PROJECT";

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";
    
    private static final String DEFAULT_ROLE = "SAMPLE_TEXT";
    private static final String UPDATED_ROLE = "UPDATED_TEXT";
    
    private static final String DEFAULT_CLIENT = "SAMPLE_TEXT";
    private static final String UPDATED_CLIENT = "UPDATED_TEXT";
    
    private static final LocalDate DEFAULT_START = new LocalDate(0L);
    private static final LocalDate UPDATED_START = new LocalDate();
    
    private static final LocalDate DEFAULT_END = new LocalDate(0L);
    private static final LocalDate UPDATED_END = new LocalDate();
    
    private static final String DEFAULT_NOTE = "SAMPLE_TEXT";
    private static final String UPDATED_NOTE = "UPDATED_TEXT";
        
    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private CompanyRepository companyRepository;

    private MockMvc restProjectMockMvc;

    private Project project;

    private Project firstProject;
    
    private Project secondProject;

    private Company firstCompany;
    
    private Company secondCompany;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectResource projectResource = new ProjectResource();
        ReflectionTestUtils.setField(projectResource, "projectRepository", projectRepository);
        this.restProjectMockMvc = MockMvcBuilders.standaloneSetup(projectResource).build();
    }

    @Before
    public void initTest() {
        project = new Project();
        project.setName(DEFAULT_NAME);
        project.setDescription(DEFAULT_DESCRIPTION);
        project.setRole(DEFAULT_ROLE);
        project.setClient(DEFAULT_CLIENT);
        project.setStart(DEFAULT_START);
        project.setEnd(DEFAULT_END);
        project.setNote(DEFAULT_NOTE);
        
        firstCompany = new Company();
        firstCompany.setName(FIRST_COMPANY_NAME);
        firstCompany.setDescription(DEFAULT_DESCRIPTION);       

        firstProject = new Project();
        firstProject.setName(FIRST_PROJECT_NAME);
        firstProject.setDescription(DEFAULT_DESCRIPTION);
        firstProject.setRole(DEFAULT_ROLE);
        firstProject.setClient(DEFAULT_CLIENT);
        firstProject.setStart(DEFAULT_START);
        firstProject.setEnd(DEFAULT_END);
        firstProject.setNote(DEFAULT_NOTE);      
        firstProject.setCompany(firstCompany);
        
        secondCompany = new Company();
        secondCompany.setName(SECOND_COMPANY_NAME);
        secondCompany.setDescription(DEFAULT_DESCRIPTION);
        
        secondProject = new Project();
        secondProject.setName(SECOND_PROJECT_NAME);
        secondProject.setDescription(DEFAULT_DESCRIPTION);
        secondProject.setRole(DEFAULT_ROLE);
        secondProject.setClient(DEFAULT_CLIENT);
        secondProject.setStart(DEFAULT_START);
        secondProject.setEnd(DEFAULT_END);
        secondProject.setNote(DEFAULT_NOTE);       
        secondProject.setCompany(secondCompany);
    }

    @Test
    @Transactional
    public void createProject() throws Exception {
        // Validate the database is empty
        assertThat(projectRepository.findAll()).hasSize(0);

        // Create the Project
        restProjectMockMvc.perform(post("/app/rest/projects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(project)))
                .andExpect(status().isOk());

        // Validate the Project in the database
        List<Project> projects = projectRepository.findAll();
        assertThat(projects).hasSize(1);
        Project testProject = projects.iterator().next();
        assertThat(testProject.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProject.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProject.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testProject.getClient()).isEqualTo(DEFAULT_CLIENT);
        assertThat(testProject.getStart()).isEqualTo(DEFAULT_START);
        assertThat(testProject.getEnd()).isEqualTo(DEFAULT_END);
        assertThat(testProject.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    public void getAllProjects() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projects
        restProjectMockMvc.perform(get("/app/rest/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(project.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.[0].role").value(DEFAULT_ROLE.toString()))
                .andExpect(jsonPath("$.[0].client").value(DEFAULT_CLIENT.toString()))
                .andExpect(jsonPath("$.[0].start").value(DEFAULT_START.toString()))
                .andExpect(jsonPath("$.[0].end").value(DEFAULT_END.toString()))
                .andExpect(jsonPath("$.[0].note").value(DEFAULT_NOTE.toString()));
    }

    @Test
    @Transactional
    public void getAllProjectsByCompany() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(firstCompany);
        projectRepository.saveAndFlush(firstProject);
        
        companyRepository.saveAndFlush(secondCompany);
        projectRepository.saveAndFlush(secondProject);        
        projectRepository.saveAndFlush(project);

        // Get all the projects of the first company
        restProjectMockMvc.perform(get("/app/rest/projects/company/{id}", firstCompany.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(firstProject.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(FIRST_PROJECT_NAME.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.[0].role").value(DEFAULT_ROLE.toString()))
                .andExpect(jsonPath("$.[0].client").value(DEFAULT_CLIENT.toString()))
                .andExpect(jsonPath("$.[0].start").value(DEFAULT_START.toString()))
                .andExpect(jsonPath("$.[0].end").value(DEFAULT_END.toString()))
                .andExpect(jsonPath("$.[0].note").value(DEFAULT_NOTE.toString()));
        //System.out.println( restProjectMockMvc.perform(get("/app/rest/projects/company/{id}", firstCompany.getId())).andDo(MockMvcResultHandlers.print()));
    }

    @Test
    @Transactional
    public void getProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get the project
        restProjectMockMvc.perform(get("/app/rest/projects/{id}", project.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(project.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()))
            .andExpect(jsonPath("$.client").value(DEFAULT_CLIENT.toString()))
            .andExpect(jsonPath("$.start").value(DEFAULT_START.toString()))
            .andExpect(jsonPath("$.end").value(DEFAULT_END.toString()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProject() throws Exception {
        // Get the project
        restProjectMockMvc.perform(get("/app/rest/projects/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Update the project
        project.setName(UPDATED_NAME);
        project.setDescription(UPDATED_DESCRIPTION);
        project.setRole(UPDATED_ROLE);
        project.setClient(UPDATED_CLIENT);
        project.setStart(UPDATED_START);
        project.setEnd(UPDATED_END);
        project.setNote(UPDATED_NOTE);
        restProjectMockMvc.perform(post("/app/rest/projects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(project)))
                .andExpect(status().isOk());

        // Validate the Project in the database
        List<Project> projects = projectRepository.findAll();
        assertThat(projects).hasSize(1);
        Project testProject = projects.iterator().next();
        assertThat(testProject.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProject.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProject.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testProject.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testProject.getStart()).isEqualTo(UPDATED_START);
        assertThat(testProject.getEnd()).isEqualTo(UPDATED_END);
        assertThat(testProject.getNote()).isEqualTo(UPDATED_NOTE);;
    }

    @Test
    @Transactional
    public void deleteProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get the project
        restProjectMockMvc.perform(delete("/app/rest/projects/{id}", project.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Project> projects = projectRepository.findAll();
        assertThat(projects).hasSize(0);
    }
}
