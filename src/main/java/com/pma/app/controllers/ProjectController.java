package com.pma.app.controllers;

import com.pma.app.entities.Project;
import com.pma.app.services.EmployeeService;
import com.pma.app.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Elimane on Mar, 2020, at 02:05
 */
@Controller
@RequestMapping("/projects")
@CrossOrigin("*")
public class ProjectController {


    private final ProjectService proServ;

    private final EmployeeService empServ;

    public ProjectController(ProjectService proServ, EmployeeService empServ) {
        this.proServ = proServ;
        this.empServ = empServ;
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model)
    {
        model.addAttribute("project", new Project());
        model.addAttribute("allEmployees", empServ.findAll());

            return "projects/new-project";
    }

//    @PostMapping("/save")
//    public String createProject(Project project, @RequestParam List<Long> employees, Model model)
//    {
//        Iterable<Employee> employeesList = empRepo.findAllById(employees);
//
//        employeesList.forEach(emp -> {
//            emp.getProjects().add(project);
//            empRepo.save(emp);
//        });
//
//        //Use redirect to prevent duplicate submissions
//        return "redirect:/home";
//    }

    @PostMapping("/save")
    public String createProject(Project project)
    {

       proServ.save(project);

        //Use redirect to prevent duplicate submissions
        return "redirect:/projects";
    }

    @GetMapping
    public String displayProjects(Model model)
    {
        model.addAttribute("projects", proServ.findAll());


        return "projects/projects";
    }

}
