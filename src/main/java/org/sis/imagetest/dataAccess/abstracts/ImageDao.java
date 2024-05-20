package org.sis.imagetest.dataAccess.abstracts;

import org.sis.imagetest.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImageDao extends JpaRepository<Image, Integer> {
    Image findById(int id);

    @Transactional
    Image findByImageUrl(String imageUrl);
}

