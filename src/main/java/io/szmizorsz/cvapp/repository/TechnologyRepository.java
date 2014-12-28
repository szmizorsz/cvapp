package io.szmizorsz.cvapp.repository;

import io.szmizorsz.cvapp.domain.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Technology entity.
 */
public interface TechnologyRepository extends JpaRepository<Technology, Long> {

}
