package io.szmizorsz.cvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.szmizorsz.cvapp.domain.Other;
import io.szmizorsz.cvapp.repositoryMongo.OtherRepository;
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
 * REST controller for managing Other.
 */
@RestController
@RequestMapping("/app")
public class OtherResource {

    private final Logger log = LoggerFactory.getLogger(OtherResource.class);

    @Inject
    private OtherRepository otherRepository;

    /**
     * POST  /rest/others -> Create a new other.
     */
    @RequestMapping(value = "/rest/others",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Other other) {
        log.debug("REST request to save Other : {}", other);
        otherRepository.save(other);
    }

    /**
     * GET  /rest/others -> get all the others.
     */
    @RequestMapping(value = "/rest/others",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Other> getAll() {
        log.debug("REST request to get all Others");
        return otherRepository.findAll();
    }

    /**
     * GET  /rest/others/:id -> get the "id" other.
     */
    @RequestMapping(value = "/rest/others/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Other> get(@PathVariable String id) {
        log.debug("REST request to get Other : {}", id);
        return Optional.ofNullable(otherRepository.findOne(id))
            .map(other -> new ResponseEntity<>(
                other,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/others/:id -> delete the "id" other.
     */
    @RequestMapping(value = "/rest/others/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable String id) {
        log.debug("REST request to delete Other : {}", id);
        otherRepository.delete(id);
    }
}
