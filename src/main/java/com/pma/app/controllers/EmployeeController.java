package com.pma.app.controllers;

import com.pma.app.dao.EmployeeRepository;
import com.pma.app.entities.Employee;
import com.pma.app.entities.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Elimane on Mar, 2020, at 18:54
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository empRepo;

    public EmployeeController(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model)
    {
        model.addAttribute("employee", new Employee());

        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Employee employee, Model model)
    {
        empRepo.save(employee);

        //model.addAttribute("project", new Project());

        //Use redirect to prevent duplicate submissions
        return "redirect:/home";
    }

    @GetMapping
    public String displayEmployees(Model model)
    {
        model.addAttribute("allEmployees", empRepo.findAll());

        return "employees/employees";
    }




}
