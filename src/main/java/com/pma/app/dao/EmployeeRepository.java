package com.pma.app.dao;

import com.pma.app.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Elimane on Mar, 2020, at 18:56
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {


}
