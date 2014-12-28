package io.szmizorsz.cvapp.repository;

import java.util.List;

import io.szmizorsz.cvapp.domain.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Project entity.
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select project from Project project left join fetch project.technologys where project.id = :id")
    Project findOneWithEagerRelationships(@Param("id") Long id);

    List<Project> findByCompanyId(Long id);
}
