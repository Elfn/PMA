package com.pma.app.dao;

import com.pma.app.dto.DataChart;
import com.pma.app.dto.EmployeeProject;
import com.pma.app.entities.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Elimane on Mar, 2020, at 18:56
 */
//@Primary//For repository one
//@Profile("prod")
@RepositoryRestResource(collectionResourceRel="restemployees",path="restemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    @Query(nativeQuery = true , value = "SELECT   e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount  FROM  employee e , project_employee pe WHERE e.employee_id  =  pe.employee_id  GROUP BY  e.first_name, e.last_name ORDER BY 3 DESC\n")
    public List<EmployeeProject> employeeProjects();

    @Query(nativeQuery = true , value = "SELECT e.status as label, COUNT(*) as value FROM Employee e GROUP BY status")
    public List<DataChart> findCountEmployeesByStatus();

    public Employee findByEmail(String email);

}
