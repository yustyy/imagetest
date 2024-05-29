package org.sis.imagetest.dataAccess.abstracts;

import org.sis.imagetest.entities.DetectedObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectedObjectDao extends JpaRepository<DetectedObject, Integer>{
}
