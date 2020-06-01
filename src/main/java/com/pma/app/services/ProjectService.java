package com.pma.app.services;

import com.pma.app.dao.ProjectRepository;
import com.pma.app.dto.DataChart;
import com.pma.app.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Elimane on Apr, 2020, at 15:52
 */
@Service
public class ProjectService implements ProjectRepository {
    //Field injection

    @Qualifier("projectRepository")
    @Autowired
    private ProjectRepository proRepo;

//Setter injection
//    @Autowired
//    public void setProRepo(@Qualifier("projectRepository") ProjectRepository proRepo) {
//        this.proRepo = proRepo;
//    }

    @Override
    public List<DataChart> findCountProjectsByStage() {
        return proRepo.findCountProjectsByStage();
    }

    @Override
    public List<Project> findProjectsNamesByEmployeeId(Long empId) {
        return proRepo.findProjectsNamesByEmployeeId(empId);
    }

    @Override
    public <S extends Project> S save(S s) {
        return proRepo.save(s);
    }

    @Override
    public <S extends Project> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Project> findById(Long aLong) {
        return proRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Project> findAll() {
        return proRepo.findAll();
    }

    @Override
    public Iterable<Project> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return proRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Project project) {

    }

    @Override
    public void deleteAll(Iterable<? extends Project> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Iterable<Project> findAll(Sort sort) {
        return proRepo.findAll(sort);
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return null;
    }
}
