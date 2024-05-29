package org.sis.imagetest.business.abstracts;

import org.sis.imagetest.core.results.DataResult;
import org.sis.imagetest.core.results.Result;
import org.sis.imagetest.entities.User;
import org.sis.imagetest.entities.dtos.RequestUserDto;

import java.util.List;

public interface UserService {

    Result add(RequestUserDto user);

    DataResult<User> getById(int id);

    DataResult<List<User>> getAll();


}
