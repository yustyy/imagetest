package org.sis.imagetest.dataAccess.abstracts;

import org.sis.imagetest.entities.DetectedTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectedTranslationDao extends JpaRepository<DetectedTranslation, Integer> {
}
