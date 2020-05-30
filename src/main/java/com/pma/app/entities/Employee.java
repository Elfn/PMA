package com.pma.app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Elimane on Mar, 2020, at 18:27
 */
@Data
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    //IDENTITY to use data.sql file
    //AUTO to use CommandLineRunner Data
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")//SEQUENCE IS MOST FASTER
    @SequenceGenerator(name="employee_seq",sequenceName="employee_seq", allocationSize = 1)
    private long employee_id;

    private String firstName;
    private String LastName;
    private String email;
    @ToString.Exclude//To avoid 500 error code when I am adding new project and assinging it to employee
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",joinColumns = @JoinColumn(name="employee_id"),inverseJoinColumns = @JoinColumn(name="project_id"))
    private List<Project> projects;
    private String status;

    public Employee(String firstName, String lastName, String email, String status) {
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.status = status;
    }

}

