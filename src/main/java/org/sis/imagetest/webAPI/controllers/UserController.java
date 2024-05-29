package org.sis.imagetest.webAPI.controllers;

import lombok.AllArgsConstructor;
import org.sis.imagetest.business.abstracts.UserService;
import org.sis.imagetest.core.results.DataResult;
import org.sis.imagetest.core.results.Result;
import org.sis.imagetest.entities.User;
import org.sis.imagetest.entities.dtos.RequestUserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/add")
    public Result addUser(@RequestBody RequestUserDto user){
        return userService.add(user);
    }

    @GetMapping("/getAll")
    public DataResult<List<User>> getAll(){
        return userService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<User> getById(int id){
        return userService.getById(id);
    }


}
