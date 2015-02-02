package io.szmizorsz.cvapp.repositoryMongo;

import io.szmizorsz.cvapp.domain.Language;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Language entity.
 */
public interface LanguageRepository extends MongoRepository<Language, String> {

}
