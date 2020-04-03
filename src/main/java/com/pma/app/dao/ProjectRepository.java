package com.pma.app.dao;

import com.pma.app.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Elimane on Mar, 2020, at 17:27
 */
public interface ProjectRepository  extends CrudRepository<Project, Long> {

    @Query(nativeQuery = true , value = "SELECT COUNT(p.project_id) FROM Project p WHERE p.stage = :stage")
    public List<Integer> findCountProjectsByStage(@Param("stage") String stage);
}
