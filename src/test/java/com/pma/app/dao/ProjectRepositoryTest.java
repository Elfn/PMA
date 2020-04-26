package com.pma.app.dao;

import com.pma.app.AppApplication;
import com.pma.app.entities.Project;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Elimane on Apr, 2020, at 21:25
 */
@SpringBootTest//For integration test
//@ContextConfiguration(classes= AppApplication.class)
@RunWith(SpringRunner.class)
//@DataJpaTest //For integration test

//To execute sql files before(data.sql) and after(drop.sql) running test
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql","classpath:data.sql"}), @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:drop.sql"})})//To work with all sql files (data.sql ...)
class ProjectRepositoryTest {
    @Qualifier("projectRepository")
    @Autowired
    ProjectRepository proRepo;

    @Test
    public void ifNewProjectSavedTest() {

        //Insert a 5th record in test db[Project table]
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
