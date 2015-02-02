package io.szmizorsz.cvapp.repositoryMongo;

import io.szmizorsz.cvapp.domain.Other;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Other entity.
 */
public interface OtherRepository extends MongoRepository<Other, String> {

}
