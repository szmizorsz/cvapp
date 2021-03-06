package io.szmizorsz.cvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.szmizorsz.cvapp.domain.Project;
import io.szmizorsz.cvapp.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Project.
 */
@RestController
@RequestMapping("/app")
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    @Inject
    private ProjectRepository projectRepository;

    /**
     * POST  /rest/projects -> Create a new project.
     */
    @RequestMapping(value = "/rest/projects",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Project project) {
        log.debug("REST request to save Project : {}", project);
        projectRepository.save(project);
    }

    /**
     * GET  /rest/projects -> get all the projects.
     */
    @RequestMapping(value = "/rest/projects",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Project> getAll() {
        log.debug("REST request to get all Projects");
        return projectRepository.findAll();
    }

    /**
     * GET  /rest/projects/:id -> get the "id" project.
     */
    @RequestMapping(value = "/rest/projects/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Project> get(@PathVariable Long id) {
        log.debug("REST request to get Project : {}", id);
        return Optional.ofNullable(projectRepository.findOneWithEagerRelationships(id))
            .map(project -> new ResponseEntity<>(
                project,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /rest/projects/company/:id -> get all the projects of the "id" company.
     */
    @RequestMapping(value = "/rest/projects/company/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Project> getByCompany(@PathVariable Long id) {
        log.debug("REST request to all the projects of the company id : {}", id);
        return projectRepository.findByCompanyId(id);        
    }

    /**
     * DELETE  /rest/projects/:id -> delete the "id" project.
     */
    @RequestMapping(value = "/rest/projects/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Project : {}", id);
        projectRepository.delete(id);
    }
}
