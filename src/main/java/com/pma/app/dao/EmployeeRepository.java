package com.pma.app.dao;

import com.pma.app.entities.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Elimane on Mar, 2020, at 18:56
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
