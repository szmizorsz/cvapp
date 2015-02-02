package io.szmizorsz.cvapp.repositoryMongo;

import io.szmizorsz.cvapp.domain.Knowledge;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Knowledge entity.
 */
public interface KnowledgeRepository extends MongoRepository<Knowledge, String> {

}
