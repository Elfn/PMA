package com.pma.app.dao;

import com.pma.app.AppApplication;
import com.pma.app.entities.Project;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.Assert.assertEquals;

/**
 * Created by Elimane on Apr, 2020, at 20:09
 */
//In order to access all classes and codes we need to load spring context
@ContextConfiguration(classes= AppApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest //For integration test
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql","classpath:data.sql"}), @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:drop.sql"})})//To work with all sql files (data.sql ...)
public class ProjectRepositoryIntegrationTest {
    @Qualifier("projectRepository")
    @Autowired
    ProjectRepository proRepo;

    @Test
    public void ifNewProjectSavedTest() {

        Project newProject = new Project();
        newProject.setName("Test");
        newProject.setDescription("New test project");
        newProject.setStage("IN PROGRESS");

        //Save
        proRepo.save(newProject);

        assertEquals(5, proRepo.count());


    }

    @Test
    void findCountProjectsByStage() {
    }

    @Test
    void findProjectsNamesByEmployeeId() {
    }

}
