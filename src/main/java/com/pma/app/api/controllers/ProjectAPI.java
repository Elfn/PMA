package com.pma.app.api.controllers;

import com.pma.app.entities.Project;
import com.pma.app.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long id)
    {
        return pjtService.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody @Valid Project project, BindingResult bindingResult) throws Exception {

        return pjtService.save(project);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project update( @RequestBody @Valid Project project)
    {
        return pjtService.save(project);
    }

    //Patch to avoid duplication and delete during update  <----UPDATE
    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Project partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Project patchProject, BindingResult bindingResult)
    {
        Project pjt =  pjtService.findById(id).get();
        if(patchProject.getName() != null)
        {
            pjt.setName(patchProject.getName());
        }

        if(patchProject.getStage() != null)
        {
            pjt.setStage(patchProject.getStage());
        }

        if(patchProject.getDescription() != null)
        {
            pjt.setDescription(patchProject.getDescription());
        }

        if(patchProject.getEmployees() != null)
        {
            pjt.setEmployees(patchProject.getEmployees());
        }



        return pjtService.save(pjt);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id)
    {
        try{
            pjtService.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){}

    }

    @GetMapping(params= {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Project> findPaginatedPojects(@RequestParam("page") int page,
                                                     @RequestParam("size") int size){
        Pageable pageAndSize = PageRequest.of(page, size);

        return pjtService.findAll(pageAndSize);
    }


}
