package io.szmizorsz.cvapp.repository;

import java.util.List;

import io.szmizorsz.cvapp.domain.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Spring Data JPA repository for the Company entity.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
    @Query("select distinct c from Company c left join fetch c.projects")
    List<Company> findAllWithEagerRelationships();

}
