package org.sis.imagetest.business.concretes;

import lombok.AllArgsConstructor;
import org.sis.imagetest.business.abstracts.DetectedObjectService;
import org.sis.imagetest.business.abstracts.UserService;
import org.sis.imagetest.core.results.*;
import org.sis.imagetest.dataAccess.abstracts.DetectedObjectDao;
import org.sis.imagetest.dataAccess.abstracts.DetectedTranslationDao;
import org.sis.imagetest.dataAccess.abstracts.UserDao;
import org.sis.imagetest.entities.DetectedObject;
import org.sis.imagetest.entities.DetectedTranslation;
import org.sis.imagetest.entities.User;
import org.sis.imagetest.entities.dtos.RequestUserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private UserDao userDao;

    private DetectedObjectDao detectedObjectDao;

    private DetectedTranslationDao detectedTranslationDao;


    @Override
    public Result add(RequestUserDto user) {

        var detectedObjects = user.getDetected_objects();

        List<DetectedObject> detectedObjectList = new ArrayList<>();

        List<DetectedTranslation> detectedTranslationList = new ArrayList<>();

        var userToSave = User.builder()
                .username(user.getUsername())
                .frame(user.getFrame())
                .build();

        userDao.save(userToSave);



        for (var detectedObject : detectedObjects) {
            detectedObjectList.add(DetectedObject.builder()
                    .cls(detectedObject.getCls())
                    .landingStatus(detectedObject.getLanding_status())
                    .topLeftX(detectedObject.getTop_left_x())
                    .topLeftY(detectedObject.getTop_left_y())
                    .bottomRightX(detectedObject.getBottom_right_x())
                    .bottomRightY(detectedObject.getBottom_right_y())
                    .user(userToSave)
                    .build());
        }

        detectedObjectDao.saveAll(detectedObjectList);

        for (var detectedTranslation : user.getDetected_translations()) {
            detectedTranslationList.add(DetectedTranslation.builder()
                            .translationY(detectedTranslation.getTranslation_y())
                            .translationX(detectedTranslation.getTranslation_x())
                            .user(userToSave)
                    .build());
        }
        detectedTranslationDao.saveAll(detectedTranslationList);





        return new SuccessResult("User added successfully");
    }

    @Override
    public DataResult<User> getById(int id) {
        var user = userDao.findById(id);

        if(user.isPresent()){
            return new SuccessDataResult<>(user.get(), "User found successfully");
        }

        return new ErrorDataResult<>("User not found");
    }

    @Override
    public DataResult<List<User>> getAll() {
        var users = userDao.findAll();

        if(users.size() == 0){
            return new SuccessDataResult<>("No user found");
        }

        return new SuccessDataResult<>(users, "Users listed successfully");
    }
}
