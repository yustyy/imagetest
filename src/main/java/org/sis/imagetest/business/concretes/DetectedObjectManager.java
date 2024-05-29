package org.sis.imagetest.business.concretes;

import lombok.AllArgsConstructor;
import org.sis.imagetest.business.abstracts.DetectedObjectService;
import org.sis.imagetest.core.results.DataResult;
import org.sis.imagetest.core.results.Result;
import org.sis.imagetest.core.results.SuccessDataResult;
import org.sis.imagetest.core.results.SuccessResult;
import org.sis.imagetest.dataAccess.abstracts.DetectedObjectDao;
import org.sis.imagetest.entities.DetectedObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DetectedObjectManager implements DetectedObjectService {

    private DetectedObjectDao detectedObjectDao;


    @Override
    public Result addDetectedObject(DetectedObject detectedObject) {
        detectedObjectDao.save(detectedObject);

        return new SuccessResult("Detected object added successfully");
    }

    @Override
    public DataResult<List<DetectedObject>> getAll() {
        var result = detectedObjectDao.findAll();

        return new SuccessDataResult<>(result, "Detected objects listed successfully");
    }

    @Override
    public DataResult<DetectedObject> getById(int id) {
        var result = detectedObjectDao.findById(id);

        if(!result.isPresent()){
            return new SuccessDataResult<>(null, "Detected object not found");
        }

        return new SuccessDataResult<>(result.get(), "Detected object listed successfully");
    }
}
