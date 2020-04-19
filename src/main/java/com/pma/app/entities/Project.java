package com.pma.app.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elimane on Mar, 2020, at 01:45
 */
@Data
@NoArgsConstructor
@Entity
public class Project {

    @Id
    //IDENTITY to use data.sql file
    //AUTO to use CommandLineRunner Data
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "project_seq")
    @SequenceGenerator(name="project_seq",sequenceName="project_seq", allocationSize = 1)
    private long project_id;
    private String name;
    private String stage; //NOTSTARTED COMPLETED INPROGRESS
    private String description;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",joinColumns = @JoinColumn(name="project_id"),inverseJoinColumns = @JoinColumn(name="employee_id"))
    private List<Employee> employees;

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public void addEmployee(Employee employee)
    {
        if(!employee.equals(null))
            employees = new ArrayList<>();


        this.employees.add(employee);
    }
}
