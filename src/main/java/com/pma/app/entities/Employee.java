package com.pma.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pma.app.validator.UniqueValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 50)
    private String LastName;

    @NotNull
    @Email(message="*Must be a valid email address")
    @UniqueValue//DOESNT WORK!!!!!
    //@Column(unique = true)
    private String email;

    @ToString.Exclude//To avoid 500 error code when I am adding new project and assinging it to employee
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",joinColumns = @JoinColumn(name="employee_id"),inverseJoinColumns = @JoinColumn(name="project_id"))
    @JsonIgnore//To avoid infinite loop between entities relashionship during serialization
    private List<Project> projects;

    private String status;

    public Employee(String firstName, String lastName, String email, String status) {
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.status = status;
    }

}

