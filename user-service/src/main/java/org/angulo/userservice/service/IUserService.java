package org.angulo.userservice.service;

import org.angulo.userservice.model.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    /* CRUD */

    // CREATE
    UserDto save(UserDto userDto);

    // READ
    List<UserDto> findAllUsers();

    UserDto findUserByEmail(String email);

    // UPDATE
    UserDto update(UserDto userDto, UUID id);

    // DELETE
    void deleteByEmail(String email);
}
