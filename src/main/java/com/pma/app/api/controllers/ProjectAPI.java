package com.pma.app.api.controllers;

import com.pma.app.entities.Employee;
import com.pma.app.entities.Project;
import com.pma.app.services.EmployeeService;
import com.pma.app.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Elimane on May, 2020, at 08:32
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectAPI {

    @Autowired
    private ProjectService pjtService;

    @GetMapping
    public Iterable<Project> getProjects()
    {
        return pjtService.findAll();
    }



}
