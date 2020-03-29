package com.pma.app.controllers;

import com.pma.app.dao.EmployeeRepository;
import com.pma.app.dao.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Elimane on Mar, 2020, at 04:39
 */
@Controller
public class HomeController {

    private  final ProjectRepository proRepo;
    private  final EmployeeRepository empRepo;

    public HomeController(ProjectRepository proRepo, EmployeeRepository empRepo) {
        this.proRepo = proRepo;
        this.empRepo = empRepo;
    }

    @GetMapping({ "/home", "/" })
    public String displayHome(Model model)
    {
       model.addAttribute("projectsNumber", proRepo.count());
        model.addAttribute("employeesNumber", empRepo.count());

        return "/main/home";
    }

//    @GetMapping("/employees/all")
//    public String displayEmployees(Model model)
//    {
//        model.addAttribute("employees", empRepo.findAll());
//
//        return "employees";
//    }
}
