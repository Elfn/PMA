package com.pma.app.validator;

import com.pma.app.dao.EmployeeRepository;
import com.pma.app.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Elimane on May, 2020, at 12:36
 */
public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    @Qualifier("employeeRepository")
    @Autowired
    EmployeeRepository empRepo;

    //Method to implement uniqueness on a specific field (EMAIL)
    @Transactional
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Entered validation method...");

        Employee emp = empRepo.findByEmail(value);

        return emp == null;
    }
}
