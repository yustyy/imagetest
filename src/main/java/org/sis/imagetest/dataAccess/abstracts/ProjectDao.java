package org.sis.imagetest.dataAccess.abstracts;

import org.sis.imagetest.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends JpaRepository<Project, Integer> {

    Project findById(int id);

    Page<Project> findAll(Pageable pageable);

    List<Project> findAll();

}
