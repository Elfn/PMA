package com.pma.app.dao;

import com.pma.app.dto.DataChart;
import com.pma.app.entities.Project;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Elimane on Mar, 2020, at 17:27
 */
//@Profile("prod")
//@Repository
public interface ProjectRepository  extends CrudRepository<Project, Long> {

//    @Query(nativeQuery = true , value = "SELECT COUNT(p.project_id) FROM Project p WHERE p.stage = :stage")
//    public List<Integer> findCountProjectsByStage(@Param("stage") String stage);

    @Query(nativeQuery = true , value = "SELECT p.stage as label, COUNT(*) as value FROM Project p GROUP BY stage")
    public List<DataChart> findCountProjectsByStage();
    @Query(nativeQuery = true , value = "SELECT p.name as name, p.stage as stage, p.description as description FROM project p LEFT JOIN project_employee pe ON p.project_id  =  pe.project_id WHERE pe.employee_id = :empId  GROUP BY name,stage,description")
    public List<Project> findProjectsNamesByEmployeeId(@Param("empId")Long empId);
}
