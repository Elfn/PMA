package com.pma.app.controllers;

import com.pma.app.dao.EmployeeRepository;
import com.pma.app.entities.Employee;
import com.pma.app.entities.Project;
import com.pma.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Elimane on Mar, 2020, at 18:54
 */
@Controller
@RequestMapping("/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    //@Qualifier("employeeService")
    private  EmployeeService empServ;


    @GetMapping("/new")
    public String displayEmployeeForm(Model model)
    {
        model.addAttribute("employee", new Employee());

        return "employees/new-employee";
    }



    @PostMapping("/save")
    public String createEmployee(Model model, @Valid Employee employee, Errors errors) {

        if(errors.hasErrors() || employee.getStatus() == "")
            return "employees/new-employee";

        // save to the database using an employee crud repository
        empServ.save(employee);

        return "redirect:/employees";
    }


    @GetMapping
    public String displayEmployees(Model model)
    {
        model.addAttribute("allEmployees", empServ.findAll());

        return "employees/employees";
    }

    @GetMapping(path = "/update")
    public String displayUpdateForm(@RequestParam("id") Long id, Model model)
    {
        Employee emp =  empServ.findById(id).get();

        model.addAttribute("employee",emp);

        return "employees/new-employee";
    }

    @GetMapping(path = "/delete")
    public String delete(@RequestParam("id") Long id, Model model)
    {
        Employee emp =  empServ.findById(id).get();

        empServ.delete(emp);

        return "redirect:/employees";
    }

}
