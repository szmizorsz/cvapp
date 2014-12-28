package io.szmizorsz.cvapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;

import io.szmizorsz.cvapp.Application;
import io.szmizorsz.cvapp.domain.Company;
import io.szmizorsz.cvapp.domain.Project;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ProjectRepositoryTest {
	
    private static final String FIRST_COMPANY_NAME = "FIRST_COMPANY";
    private static final String SECOND_COMPANY_NAME = "SECOND_COMPANY";
    private static final String FIRST_PROJECT_NAME = "FIRST_PROJECT";
    private static final String SECOND_PROJECT_NAME = "SECOND_PROJECT";

    
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

    private Project firstProject;
    
    private Project secondProject;

    private Company firstCompany;
    
    private Company secondCompany;


	@Before
	public void setUp() throws Exception {
        firstCompany = new Company();
        firstCompany.setName(FIRST_COMPANY_NAME);
        firstCompany.setDescription(DEFAULT_DESCRIPTION);       
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
        
        secondCompany = new Company();
        secondCompany.setName(SECOND_COMPANY_NAME);
        secondCompany.setDescription(DEFAULT_DESCRIPTION);
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
	}

	@Test
	public final void testFindByCompanyId() {
		List<Project> projects = projectRepository.findByCompanyId(firstCompany.getId());
	    assertThat(projects).hasSize(1);
	    
	    Project testProjecty = projects.iterator().next();
	    assertThat(testProjecty.getName()).isEqualTo(FIRST_PROJECT_NAME);
	    assertThat(testProjecty.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
	}

}
