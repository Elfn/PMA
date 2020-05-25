package com.pma.app.controllers;

import com.pma.app.dao.EmployeeRepository;
import com.pma.app.entities.Employee;
import com.pma.app.entities.Project;
import com.pma.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String createEmployee(Employee employee, Model model)
    {
        empServ.save(employee);

        //Use redirect to prevent duplicate submissions
        return "redirect:/employees";
    }

    @GetMapping
    public String displayEmployees(Model model)
    {
        model.addAttribute("allEmployees", empServ.findAll());

        return "employees/employees";
    }




}
