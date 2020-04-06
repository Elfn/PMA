package com.pma.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pma.app.dao.EmployeeRepository;
import com.pma.app.dao.ProjectRepository;
import com.pma.app.dto.DataChart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String displayHome(Model model) throws JsonProcessingException {
        //Map<String, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<DataChart> dataChartForProjects = proRepo.findCountProjectsByStage();
        List<DataChart> dataChartForEmployees = empRepo.findCountEmployeesByStatus();

        //Into JSON conversion
//        dataChart.forEach(data -> {
//            map.put(data.getLabel(),data.getValue());
//        } );
        String JsonChartDataProjects = objectMapper.writeValueAsString(dataChartForProjects);
        String JsonChartDataEmployees = objectMapper.writeValueAsString(dataChartForEmployees);

        model.addAttribute("projectsNumber", proRepo.count());
        model.addAttribute("employeesNumber", empRepo.count());
//        model.addAttribute("notStartedProjectsNumber", proRepo.findCountProjectsByStage("NOTSTARTED"));
//        model.addAttribute("inProgressProjectsNumber", proRepo.findCountProjectsByStage("INPROGRESS"));
//        model.addAttribute("completedProjectsNumber", proRepo.findCountProjectsByStage("COMPLETED"));
        model.addAttribute("projectsByCountedStage", JsonChartDataProjects);
        model.addAttribute("EmployeesByCountedStatus", JsonChartDataEmployees);
        model.addAttribute("employeesProjectCount", empRepo.employeeProjects());

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
