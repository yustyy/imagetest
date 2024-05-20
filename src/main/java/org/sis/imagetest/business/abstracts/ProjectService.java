package org.sis.imagetest.business.abstracts;

import org.sis.imagetest.core.results.DataResult;
import org.sis.imagetest.core.results.Result;
import org.sis.imagetest.entities.Project;
import org.sis.imagetest.entities.dtos.RequestProjectDto;
import org.sis.imagetest.entities.dtos.ResponseProjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Result addProject(RequestProjectDto requestProjectDto, MultipartFile image);

    Result updateProject(RequestProjectDto requestProjectDto);

    DataResult <List<ResponseProjectDto>> getProjects(Optional<Integer> numberOfProjects);

    DataResult<Project> getProjectById(int id);

    Result deleteProject(int id);


}
