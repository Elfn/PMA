package com.pma.app.dao;

import com.pma.app.entities.Project;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Elimane on Mar, 2020, at 17:27
 */
public interface ProjectRepository  extends CrudRepository<Project, Long> {
}
