package io.szmizorsz.cvapp.web.rest;

import java.util.HashSet;
import java.util.Set;

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

import io.szmizorsz.cvapp.Application;
import io.szmizorsz.cvapp.domain.Company;
import io.szmizorsz.cvapp.domain.Project;
import io.szmizorsz.cvapp.repository.CompanyRepository;
import io.szmizorsz.cvapp.repository.ProjectRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the PublicCompanyResource REST controller.
 *
 * @see ProjectResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PublicCompanyResourceT {

	private static final String FIRST_COMPANY_NAME = "FIRST_COMPANY";
    private static final String SECOND_COMPANY_NAME = "SECOND_COMPANY";
    private static final String FIRST_PROJECT_NAME = "FIRST_PROJECT";
    private static final String SECOND_PROJECT_NAME = "SECOND_PROJECT";

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    
    private static final String DEFAULT_ROLE = "SAMPLE_TEXT";
    
    private static final String DEFAULT_CLIENT = "SAMPLE_TEXT";
    
    private static final LocalDate DEFAULT_START = new LocalDate(0L);
    
    private static final LocalDate DEFAULT_END = new LocalDate(0L);
    
    private static final String DEFAULT_NOTE = "SAMPLE_TEXT";
        
    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private CompanyRepository companyRepository;

    private MockMvc restPublicCompanyMockMvc;

    private Project project;

    private Project firstProject;
    
    private Project secondProject;

    private Company firstCompany;
    
    private Company secondCompany;
    
    private Set<Project> firstCompaniesProjects;

    private Set<Project> secondCompaniesProjects;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        PublicResource pubilcCompanyResource = new PublicResource();
        ReflectionTestUtils.setField(pubilcCompanyResource, "companyRepository", companyRepository);
        this.restPublicCompanyMockMvc = MockMvcBuilders.standaloneSetup(pubilcCompanyResource).build();
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
        firstCompaniesProjects = new HashSet<>();
        firstCompany.setProjects(firstCompaniesProjects);
        companyRepository.saveAndFlush(firstCompany);

        firstProject = new Project();
        firstProject.setName(FIRST_PROJECT_NAME);
        firstProject.setDescription(DEFAULT_DESCRIPTION);
        firstProject.setRole(DEFAULT_ROLE);
        firstProject.setClient(DEFAULT_CLIENT);
        firstProject.setStart(DEFAULT_START);
        firstProject.setEnd(DEFAULT_END);
        firstProject.setNote(DEFAULT_NOTE);      
        firstProject.setCompany(firstCompany);
        projectRepository.saveAndFlush(firstProject);

        firstCompany.getProjects().add(firstProject);
        
        secondCompany = new Company();
        secondCompany.setName(SECOND_COMPANY_NAME);
        secondCompany.setDescription(DEFAULT_DESCRIPTION);
        secondCompaniesProjects = new HashSet<>();
        secondCompany.setProjects(secondCompaniesProjects);
        companyRepository.saveAndFlush(secondCompany);
        
        secondProject = new Project();
        secondProject.setName(SECOND_PROJECT_NAME);
        secondProject.setDescription(DEFAULT_DESCRIPTION);
        secondProject.setRole(DEFAULT_ROLE);
        secondProject.setClient(DEFAULT_CLIENT);
        secondProject.setStart(DEFAULT_START);
        secondProject.setEnd(DEFAULT_END);
        secondProject.setNote(DEFAULT_NOTE);       
        secondProject.setCompany(secondCompany);
        projectRepository.saveAndFlush(secondProject);   
        
        secondCompany.getProjects().add(secondProject);
    }

    @Test
    @Transactional
    public void testGetAll() throws Exception {
    	
        //System.out.println( restPublicCompanyMockMvc.perform(get("/public/rest/companys")).andDo(MockMvcResultHandlers.print()));
        
        // Check the first project of the first company
        restPublicCompanyMockMvc.perform(get("/public/rest/companys"))
        		.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))              
                .andExpect(jsonPath("$.[0].name").value(FIRST_COMPANY_NAME.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.[0].projects.[0].id").value(firstProject.getId().intValue()))
                .andExpect(jsonPath("$.[0].projects.[0].name").value(FIRST_PROJECT_NAME.toString()));
                        
    }

}
