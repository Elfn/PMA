package com.pma.app.services;

import com.pma.app.dao.EmployeeRepository;
import com.pma.app.dao.ProjectRepository;
import com.pma.app.entities.Employee;
import com.pma.app.entities.Project;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Elimane on May, 2020, at 19:05
 */
@Service
public class ProjectEmployeeService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectEmployeeService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void assignProjectToEmployee(String employeeId, String projectId)
    {
        Project projectFound = projectRepository.findById(Long.valueOf(projectId)).get();
        Employee employeeFound = employeeRepository.findById(Long.valueOf(employeeId)).get();

        projectFound.getEmployees().add(employeeFound);
        employeeFound.getProjects().add(projectFound);

        employeeRepository.save(employeeFound);
        projectRepository.save(projectFound);
    }
}
