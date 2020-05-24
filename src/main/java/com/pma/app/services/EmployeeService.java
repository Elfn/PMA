package com.pma.app.services;

import com.pma.app.dao.EmployeeRepository;
import com.pma.app.dto.DataChart;
import com.pma.app.dto.EmployeeProject;
import com.pma.app.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Elimane on Apr, 2020, at 15:52
 */
@Service
public class EmployeeService implements EmployeeRepository{

    @Autowired
    //@Qualifier(value = "employeeRepository")
    private  EmployeeRepository empRepo;



    //    @Autowired
//    public void setEmpRepo(@Qualifier("employeeRepository") EmployeeRepository empRepo) {
//        this.empRepo = empRepo;
//    }


    @Override
    public List<EmployeeProject> employeeProjects() {
        return empRepo.employeeProjects();
    }

    @Override
    public List<DataChart> findCountEmployeesByStatus() {
        return empRepo.findCountEmployeesByStatus();
    }

    @Override
    public <S extends Employee> S save(S s) {
        return empRepo.save(s);
    }

    @Override
    public <S extends Employee> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Employee> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Employee> findAll() {
        return empRepo.findAll();
    }

    @Override
    public Iterable<Employee> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return empRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Employee employee) {

    }

    @Override
    public void deleteAll(Iterable<? extends Employee> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
