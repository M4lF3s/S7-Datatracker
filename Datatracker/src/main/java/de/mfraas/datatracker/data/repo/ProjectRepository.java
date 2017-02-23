package de.mfraas.datatracker.data.repo;

import de.mfraas.datatracker.data.bean.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marcelfraas on 26.01.17.
 */

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    Project save(Project p);
}
