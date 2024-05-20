package org.sis.imagetest.business.abstracts;

import org.sis.imagetest.core.results.DataResult;
import org.sis.imagetest.core.results.Result;
import org.sis.imagetest.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    DataResult<Image> addImage(MultipartFile file);

    DataResult<List<Image>> getImages();

    DataResult<Image> getImageById(int id);

    Result deleteImage(int id);

    DataResult<Image> getImageByImageUrl(String url);


}
