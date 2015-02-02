package io.szmizorsz.cvapp.web.rest;

import com.codahale.metrics.annotation.Timed;

import io.szmizorsz.cvapp.domain.Education;
import io.szmizorsz.cvapp.domain.Knowledge;
import io.szmizorsz.cvapp.domain.Language;
import io.szmizorsz.cvapp.domain.Other;
import io.szmizorsz.cvapp.repository.CompanyRepository;
import io.szmizorsz.cvapp.repositoryMongo.EducationRepository;
import io.szmizorsz.cvapp.repositoryMongo.KnowledgeRepository;
import io.szmizorsz.cvapp.repositoryMongo.LanguageRepository;
import io.szmizorsz.cvapp.repositoryMongo.OtherRepository;
import io.szmizorsz.cvapp.web.rest.dto.CompanyWithProjectsDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing public Company.
 */
@RestController
@RequestMapping("/public")
public class PublicResource {
	
    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    @Inject
    private CompanyRepository companyRepository;
    
    @Inject
    private EducationRepository educationRepository;

    @Inject
    private LanguageRepository languageRepository;
    
    @Inject
    private KnowledgeRepository knowledgeRepository;

    @Inject
    private OtherRepository otherRepository;

    /**
     * GET  /rest/companys -> get all the public companies with their projects.
     */
    @RequestMapping(value = "/rest/companys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CompanyWithProjectsDTO> getAllCompanyWithProjects() {
        log.debug("REST request to get all Companys with Projects and Technolgies");
        return companyRepository.findAllWithEagerRelationships()        		        		
                .stream()
                .map(company -> new CompanyWithProjectsDTO(
                            			company.getName(),
                            			company.getDescription(),
                            			company.getProjects()
                            				.stream()
                            				.collect(Collectors.toList())
                            			))
                .collect(Collectors.toList());
    }
    
    /**
     * GET  /rest/educations -> get all the educations.
     */
    @RequestMapping(value = "/rest/educations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Education> getAllEducations() {
        log.debug("REST request to get all Educations");
        return educationRepository.findAll(new Sort(Sort.Direction.DESC, "periodEn"));
    }

    /**
     * GET  /rest/languages -> get all the languages.
     */
    @RequestMapping(value = "/rest/languages",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Language> getAllLanguages() {
        log.debug("REST request to get all Languages");
        return languageRepository.findAll();
    }

    /**
     * GET  /rest/knowledges -> get all the knowledges.
     */
    @RequestMapping(value = "/rest/knowledges",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Knowledge> getAllKnowledges() {
        log.debug("REST request to get all Knowledges");
        return knowledgeRepository.findAll();
    }
    
    /**
     * GET  /rest/others -> get all the others.
     */
    @RequestMapping(value = "/rest/others",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Other> getAllOthers() {
        log.debug("REST request to get all Others");
        return otherRepository.findAll();
    }


}
