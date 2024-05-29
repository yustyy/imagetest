package org.sis.imagetest.business.abstracts;

import org.sis.imagetest.core.results.DataResult;
import org.sis.imagetest.core.results.Result;
import org.sis.imagetest.entities.DetectedObject;

import java.util.List;

public interface DetectedObjectService {

    Result addDetectedObject(DetectedObject detectedObject);

    DataResult<List<DetectedObject>> getAll();

    DataResult<DetectedObject> getById(int id);
}
