package io.szmizorsz.cvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.szmizorsz.cvapp.domain.Education;
import io.szmizorsz.cvapp.repositoryMongo.EducationRepository;
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
 * REST controller for managing Education.
 */
@RestController
@RequestMapping("/app")
public class EducationResource {

    private final Logger log = LoggerFactory.getLogger(EducationResource.class);

    @Inject
    private EducationRepository educationRepository;

    /**
     * POST  /rest/educations -> Create a new education.
     */
    @RequestMapping(value = "/rest/educations",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Education education) {
        log.debug("REST request to save Education : {}", education);
        educationRepository.save(education);
    }

    /**
     * GET  /rest/educations -> get all the educations.
     */
    @RequestMapping(value = "/rest/educations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Education> getAll() {
        log.debug("REST request to get all Educations");
        return educationRepository.findAll();
    }

    /**
     * GET  /rest/educations/:id -> get the "id" education.
     */
    @RequestMapping(value = "/rest/educations/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Education> get(@PathVariable String id) {
        log.debug("REST request to get Education : {}", id);
        return Optional.ofNullable(educationRepository.findOne(id))
            .map(education -> new ResponseEntity<>(
                education,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/educations/:id -> delete the "id" education.
     */
    @RequestMapping(value = "/rest/educations/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable String id) {
        log.debug("REST request to delete Education : {}", id);
        educationRepository.delete(id);
    }
}
