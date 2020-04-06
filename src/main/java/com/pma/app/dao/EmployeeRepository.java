package com.pma.app.dao;

import com.pma.app.dto.DataChart;
import com.pma.app.dto.EmployeeProject;
import com.pma.app.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Elimane on Mar, 2020, at 18:56
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(nativeQuery = true , value = "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount  FROM  employee e LEFT JOIN project_employee pe ON e.employee_id  =  pe.employee_id  GROUP BY  e.first_name, e.last_name ORDER BY 3 DESC\n")
    public List<EmployeeProject> employeeProjects();

    @Query(nativeQuery = true , value = "SELECT e.status as label, COUNT(*) as value FROM Employee e GROUP BY status")
    public List<DataChart> findCountEmployeesByStatus();

}
