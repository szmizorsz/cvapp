package io.szmizorsz.cvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.szmizorsz.cvapp.domain.Knowledge;
import io.szmizorsz.cvapp.repositoryMongo.KnowledgeRepository;
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
 * REST controller for managing Knowledge.
 */
@RestController
@RequestMapping("/app")
public class KnowledgeResource {

    private final Logger log = LoggerFactory.getLogger(KnowledgeResource.class);

    @Inject
    private KnowledgeRepository knowledgeRepository;

    /**
     * POST  /rest/knowledges -> Create a new knowledge.
     */
    @RequestMapping(value = "/rest/knowledges",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Knowledge knowledge) {
        log.debug("REST request to save Knowledge : {}", knowledge);
        knowledgeRepository.save(knowledge);
    }

    /**
     * GET  /rest/knowledges -> get all the knowledges.
     */
    @RequestMapping(value = "/rest/knowledges",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Knowledge> getAll() {
        log.debug("REST request to get all Knowledges");
        return knowledgeRepository.findAll();
    }

    /**
     * GET  /rest/knowledges/:id -> get the "id" knowledge.
     */
    @RequestMapping(value = "/rest/knowledges/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Knowledge> get(@PathVariable String id) {
        log.debug("REST request to get Knowledge : {}", id);
        return Optional.ofNullable(knowledgeRepository.findOne(id))
            .map(knowledge -> new ResponseEntity<>(
                knowledge,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/knowledges/:id -> delete the "id" knowledge.
     */
    @RequestMapping(value = "/rest/knowledges/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable String id) {
        log.debug("REST request to delete Knowledge : {}", id);
        knowledgeRepository.delete(id);
    }
}
