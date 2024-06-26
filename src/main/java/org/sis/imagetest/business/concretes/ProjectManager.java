package org.sis.imagetest.business.concretes;

import org.sis.imagetest.business.abstracts.ImageService;
import org.sis.imagetest.business.abstracts.ProjectService;
import org.sis.imagetest.business.constants.ProjectMessages;
import org.sis.imagetest.core.results.*;
import org.sis.imagetest.dataAccess.abstracts.ProjectDao;
import org.sis.imagetest.entities.Image;
import org.sis.imagetest.entities.Project;
import org.sis.imagetest.entities.dtos.RequestProjectDto;
import org.sis.imagetest.entities.dtos.ResponseProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectManager implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ImageService imageService;

    @Value("${api.url}")
    private String API_URL;

    @Override
    public Result addProject(RequestProjectDto requestProjectDto, MultipartFile image) {

        if (requestProjectDto.getTranslation_x() == 0){
            return new ErrorResult(ProjectMessages.translationXCanotBeNull);
        }
        if (requestProjectDto.getTranslation_y() == 0){
            return new ErrorResult(ProjectMessages.translationYCanotBeNull);
        }

        Image projectImage = null;

        if(image != null){
            var imageResult = imageService.addImage(image);
            if (!imageResult.isSuccess()){
                return new ErrorResult(imageResult.getMessage());
            }

            projectImage = imageResult.getData();
        }

        var projectToSave = Project.builder()
                .image(projectImage)
                .translationY(requestProjectDto.getTranslation_y())
                .translationX(requestProjectDto.getTranslation_x())
                .build();

        projectDao.save(projectToSave);
        return new SuccessResult(ProjectMessages.projectAddSuccess);

    }
    @Override
    public Result updateProject(RequestProjectDto requestProjectDto) {

//            var result = getProjectById(requestProjectDto.getId());
//
//            if (!result.isSuccess()){
//                return result;
//            }
//
//            var projectToUpdate = result.getData();
//
//            projectToUpdate.setContext(requestProjectDto.getContext()==null?projectToUpdate.getContext():requestProjectDto.getContext());
//            projectToUpdate.setDate(requestProjectDto.getDate()==null?projectToUpdate.getDate():requestProjectDto.getDate());
//            projectToUpdate.setDescription(requestProjectDto.getDescription()==null?projectToUpdate.getDescription():requestProjectDto.getDescription());
//            projectToUpdate.setName(requestProjectDto.getName()==null?projectToUpdate.getName():requestProjectDto.getName());

//            projectDao.save(projectToUpdate);

            var project = projectDao.findById(requestProjectDto.getId());

            if (project == null){
                return new ErrorResult(ProjectMessages.projectNotFound);
            }

            projectDao.save(project);

            return new SuccessResult(ProjectMessages.projectAddSuccess);
    }

    @Override
    public DataResult<List<ResponseProjectDto>> getProjects(Optional<Integer> numberOfProjects) {

        List<Project> result = new ArrayList<>();
        if(numberOfProjects.isPresent()) {
            result = projectDao.findAll(PageRequest.of(0, numberOfProjects.get())).toList();
        }
        else {

            result = projectDao.findAll();
        }

        if (result == null){
            return new ErrorDataResult<>(ProjectMessages.PorjectsNotFound);
        }

        var projectResult = result.stream().map(project -> ResponseProjectDto.builder()
                .id(project.getId())
                .image_url(project.getImage()==null? null : API_URL+"/api/images/getImageByUrl/"+project.getImage().getImageUrl())
                .translation_x(project.getTranslationX())
                .translation_y(project.getTranslationY())
                .build())
                .toList();

        return new SuccessDataResult<List<ResponseProjectDto>>(projectResult, ProjectMessages.getProjectsSuccess);
    }

    @Override
    public DataResult<ResponseProjectDto> getProjectById(int id) {
        var result = projectDao.findById(id);

        if(result == null){
            return new ErrorDataResult<>(ProjectMessages.projectNotFound);
        }

        var project = ResponseProjectDto.builder()
                .id(result.getId())
                .image_url(result.getImage()==null? null : API_URL+"/api/images/getImageByUrl/"+result.getImage().getImageUrl())
                .translation_x(result.getTranslationX())
                .translation_y(result.getTranslationY())
                .build();

        return new SuccessDataResult<ResponseProjectDto>(project, ProjectMessages.getProjectSuccess);
    }
    //dont repeat yourself chain
    @Override
    public Result deleteProject(int id) {

//        var result = getProjectById(id);
//
//        if (!result.isSuccess()){
//            return result;
//        }

        var result = projectDao.findById(id);

        if (result == null){
            return new ErrorResult(ProjectMessages.projectNotFound);
        }
        projectDao.delete(result);
        return new SuccessResult(ProjectMessages.deleteProjectsuccess);

    }

}
