package io.szmizorsz.cvapp.web.rest;

import com.codahale.metrics.annotation.Timed;

import io.szmizorsz.cvapp.domain.Language;
import io.szmizorsz.cvapp.repositoryMongo.LanguageRepository;

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
 * REST controller for managing Language.
 */
@RestController
@RequestMapping("/app")
public class LanguageResource {

    private final Logger log = LoggerFactory.getLogger(LanguageResource.class);

    @Inject
    private LanguageRepository languageRepository;

    /**
     * POST  /rest/languages -> Create a new language.
     */
    @RequestMapping(value = "/rest/languages",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Language language) {
        log.debug("REST request to save Language : {}", language);
        languageRepository.save(language);
    }

    /**
     * GET  /rest/languages -> get all the languages.
     */
    @RequestMapping(value = "/rest/languages",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Language> getAll() {
        log.debug("REST request to get all Languages");
        return languageRepository.findAll();
    }

    /**
     * GET  /rest/languages/:id -> get the "id" language.
     */
    @RequestMapping(value = "/rest/languages/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Language> get(@PathVariable String id) {
        log.debug("REST request to get Language : {}", id);
        return Optional.ofNullable(languageRepository.findOne(id))
            .map(language -> new ResponseEntity<>(
                language,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/languages/:id -> delete the "id" language.
     */
    @RequestMapping(value = "/rest/languages/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable String id) {
        log.debug("REST request to delete Language : {}", id);
        languageRepository.delete(id);
    }
}
