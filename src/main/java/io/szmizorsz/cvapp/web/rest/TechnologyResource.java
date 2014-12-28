package io.szmizorsz.cvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.szmizorsz.cvapp.domain.Technology;
import io.szmizorsz.cvapp.repository.TechnologyRepository;
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
 * REST controller for managing Technology.
 */
@RestController
@RequestMapping("/app")
public class TechnologyResource {

    private final Logger log = LoggerFactory.getLogger(TechnologyResource.class);

    @Inject
    private TechnologyRepository technologyRepository;

    /**
     * POST  /rest/technologys -> Create a new technology.
     */
    @RequestMapping(value = "/rest/technologys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Technology technology) {
        log.debug("REST request to save Technology : {}", technology);
        technologyRepository.save(technology);
    }

    /**
     * GET  /rest/technologys -> get all the technologys.
     */
    @RequestMapping(value = "/rest/technologys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Technology> getAll() {
        log.debug("REST request to get all Technologys");
        return technologyRepository.findAll();
    }

    /**
     * GET  /rest/technologys/:id -> get the "id" technology.
     */
    @RequestMapping(value = "/rest/technologys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Technology> get(@PathVariable Long id) {
        log.debug("REST request to get Technology : {}", id);
        return Optional.ofNullable(technologyRepository.findOne(id))
            .map(technology -> new ResponseEntity<>(
                technology,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/technologys/:id -> delete the "id" technology.
     */
    @RequestMapping(value = "/rest/technologys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Technology : {}", id);
        technologyRepository.delete(id);
    }
}
