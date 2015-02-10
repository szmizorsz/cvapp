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
        firstCompany.setNameEn(FIRST_COMPANY_NAME);
        firstCompany.setDescriptionEn(DEFAULT_DESCRIPTION);       
        companyRepository.saveAndFlush(firstCompany);

        firstProject = new Project();
        firstProject.setNameEn(FIRST_PROJECT_NAME);
        firstProject.setDescriptionEn(DEFAULT_DESCRIPTION);
        firstProject.setClientEn(DEFAULT_CLIENT);
        firstProject.setStart(DEFAULT_START);
        firstProject.setEnd(DEFAULT_END);
        firstProject.setCompany(firstCompany);
        projectRepository.saveAndFlush(firstProject);
        
        secondCompany = new Company();
        secondCompany.setNameEn(SECOND_COMPANY_NAME);
        secondCompany.setDescriptionEn(DEFAULT_DESCRIPTION);
        companyRepository.saveAndFlush(secondCompany);
        
        secondProject = new Project();
        secondProject.setNameEn(SECOND_PROJECT_NAME);
        secondProject.setDescriptionEn(DEFAULT_DESCRIPTION);
        secondProject.setClientEn(DEFAULT_CLIENT);
        secondProject.setStart(DEFAULT_START);
        secondProject.setEnd(DEFAULT_END);
        secondProject.setCompany(secondCompany);
        projectRepository.saveAndFlush(secondProject);        
	}

	@Test
	public final void testFindByCompanyId() {
		List<Project> projects = projectRepository.findByCompanyId(firstCompany.getId());
	    assertThat(projects).hasSize(1);
	    
	    Project testProjecty = projects.iterator().next();
	    assertThat(testProjecty.getNameEn()).isEqualTo(FIRST_PROJECT_NAME);
	    assertThat(testProjecty.getDescriptionEn()).isEqualTo(DEFAULT_DESCRIPTION);
	}

}
