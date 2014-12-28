package io.szmizorsz.cvapp.web.rest;

import com.codahale.metrics.annotation.Timed;

import io.szmizorsz.cvapp.repository.CompanyRepository;
import io.szmizorsz.cvapp.web.rest.dto.CompanyWithProjectsDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PublicCompanyResource {
	
    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    @Inject
    private CompanyRepository companyRepository;

    /**
     * GET  /rest/companys -> get all the public companies with their projects.
     */
    @RequestMapping(value = "/rest/companys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CompanyWithProjectsDTO> getAll() {
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

}
