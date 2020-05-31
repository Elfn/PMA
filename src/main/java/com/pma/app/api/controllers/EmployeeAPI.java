package com.pma.app.api.controllers;

import com.pma.app.entities.Employee;
import com.pma.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by Elimane on May, 2020, at 19:29
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeAPI {
    @Autowired
    private EmployeeService empService;

    @GetMapping
    public Iterable<Employee> getEmployees()
    {
        return empService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id)
    {
        return empService.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee employee, BindingResult bindingResult) throws Exception {

        return empService.save(employee);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update( @RequestBody @Valid Employee employee)
    {
        return empService.save(employee);
    }

    //Patch to avoid duplication and delete during update  <----UPDATE
    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Employee partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Employee patchEmployee, BindingResult bindingResult)
    {
       Employee emp =  empService.findById(id).get();
       if(patchEmployee.getFirstName() != null)
       {
            emp.setFirstName(patchEmployee.getFirstName());
       }

        if(patchEmployee.getLastName() != null)
        {
            emp.setLastName(patchEmployee.getLastName());
        }

        if(patchEmployee.getEmail() != null)
        {
            emp.setEmail(patchEmployee.getEmail());
        }

        if(patchEmployee.getStatus() != null)
        {
            emp.setStatus(patchEmployee.getStatus());
        }

        if(patchEmployee.getProjects() != null)
        {
            emp.setProjects(patchEmployee.getProjects());
        }

       return empService.save(emp);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id)
    {
        try{
            empService.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){}

    }

    @GetMapping(params= {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page,
                                                     @RequestParam("size") int size){
        Pageable pageAndSize = PageRequest.of(page, size);

        return empService.findAll(pageAndSize);
    }
}
