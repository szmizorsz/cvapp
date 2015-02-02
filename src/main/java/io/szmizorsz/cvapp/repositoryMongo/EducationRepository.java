package io.szmizorsz.cvapp.repositoryMongo;

import java.util.List;

import io.szmizorsz.cvapp.domain.Education;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Education entity.
 */
public interface EducationRepository extends MongoRepository<Education, String> {	

}
