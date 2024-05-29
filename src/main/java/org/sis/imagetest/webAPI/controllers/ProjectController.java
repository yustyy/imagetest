package org.sis.imagetest.webAPI.controllers;


import org.apache.coyote.Response;
import org.sis.imagetest.business.abstracts.ProjectService;
import org.sis.imagetest.core.results.DataResult;
import org.sis.imagetest.core.results.Result;
import org.sis.imagetest.entities.Project;
import org.sis.imagetest.entities.dtos.RequestProjectDto;
import org.sis.imagetest.entities.dtos.ResponseProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/addProject")
    public Result addProject(
            @RequestPart("data") RequestProjectDto requestProjectDto,
            @RequestPart("image") MultipartFile image) {
        return projectService.addProject(requestProjectDto, image);
    }
    @PostMapping("/update")
    public Result updateProject(@RequestBody RequestProjectDto requestProjectDto) {
        return projectService.updateProject(requestProjectDto);
    }
    @GetMapping(value = { "/getProjects/{numberOfProjects}", "/getProjects" })
    public DataResult<List<ResponseProjectDto>> getProjects(@PathVariable Optional<Integer> numberOfProjects){
        return projectService.getProjects(numberOfProjects);
    }
    @GetMapping("/getById")
    public DataResult<ResponseProjectDto> getProjectById(@RequestParam int id){
        return projectService.getProjectById(id);
    }
    @PostMapping("/delete")
    public Result deleteProject(@RequestParam int id){
        return projectService.deleteProject(id);
    }

}
