package com.pma.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pma.app.dao.EmployeeRepository;
import com.pma.app.dao.ProjectRepository;
import com.pma.app.dto.DataChart;
import com.pma.app.entities.Employee;
import com.pma.app.services.EmployeeService;
import com.pma.app.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Elimane on Mar, 2020, at 04:39
 */
@Controller
@Slf4j
@CrossOrigin("*")
public class HomeController {


    private final ProjectService proServ;

    private final EmployeeService empServ;

    public HomeController(ProjectService proServ, EmployeeService empServ) {
        this.proServ = proServ;
        this.empServ = empServ;
    }

    @Value("${application.name}")
    private String applicationName;

    @GetMapping({ "/home", "/" })
    public String displayHome(Model model) throws JsonProcessingException {
        //Map<String, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<DataChart> dataChartForProjects = proServ.findCountProjectsByStage();
        List<DataChart> dataChartForEmployees = empServ.findCountEmployeesByStatus();

        //Into JSON conversion
//        dataChart.forEach(data -> {
//            map.put(data.getLabel(),data.getValue());
//        } );
        String JsonChartDataProjects = objectMapper.writeValueAsString(dataChartForProjects);
        String JsonChartDataEmployees = objectMapper.writeValueAsString(dataChartForEmployees);

        model.addAttribute("appName", applicationName);
        model.addAttribute("projectsNumber", proServ.count());
        model.addAttribute("employeesNumber", empServ.count());
//        model.addAttribute("notStartedProjectsNumber", proRepo.findCountProjectsByStage("NOTSTARTED"));
//        model.addAttribute("inProgressProjectsNumber", proRepo.findCountProjectsByStage("INPROGRESS"));
//        model.addAttribute("completedProjectsNumber", proRepo.findCountProjectsByStage("COMPLETED"));
        model.addAttribute("projectsByCountedStage", JsonChartDataProjects);
        model.addAttribute("EmployeesByCountedStatus", JsonChartDataEmployees);
        model.addAttribute("employeesProjectCount", empServ.employeeProjects());


        return "main/home";
    }

//    @GetMapping("/employees/all")
//    public String displayEmployees(Model model)
//    {
//        model.addAttribute("employees", empRepo.findAll());
//
//        return "employees";
//    }




    @GetMapping("/employee/projects/{empId}")
    public void listprojects(@PathVariable Long empId, Model model) throws Exception {
        log.debug("Getting projects list for recipe id: " + empId);

        Employee emp = empServ.findById(empId).get();

        if(emp == null)
        {
            throw new Exception("Any Employee found!!");
        }

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("projects", proServ.findProjectsNamesByEmployeeId(emp.getEmployee_id()));

       // return null;
    }

}
