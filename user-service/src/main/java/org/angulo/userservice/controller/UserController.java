package org.angulo.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.angulo.userservice.model.dto.ResponseDto;
import org.angulo.userservice.model.dto.UserDto;
import org.angulo.userservice.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers(){
        log.info("Testing retry pattern");
        List<UserDto> users = userService.findAllUsers();
        ResponseDto<List<UserDto>> response = new ResponseDto<>(
                OK,
                "All users registered",
                users
        );
        return ResponseEntity.status(OK).body(response);
    }

    @GetMapping("/{email}")
    public ResponseEntity<ResponseDto<UserDto>> getUserByEmail(@PathVariable String email){
        UserDto user = userService.findUserByEmail(email);
        ResponseDto<UserDto> response = new ResponseDto<>(
                OK,
                "User by email",
                user
        );
        return ResponseEntity.status(OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDto<UserDto>> createUser(@RequestBody UserDto userDto){
        UserDto newUser = userService.save(userDto);
        ResponseDto<UserDto> response = new ResponseDto<>(
                CREATED,
                "User created successfully",
                newUser
        );
        return ResponseEntity.status(CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDto<UserDto>> updateUser(@RequestParam UUID id, @RequestBody UserDto userDto){
        UserDto userUpdated = userService.update(userDto, id);
        ResponseDto<UserDto> response = new ResponseDto<>(
                OK,
                "User updated successfully",
                userUpdated
        );
        return ResponseEntity.status(OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<?>> deleteUser(@RequestParam String email){
        userService.deleteByEmail(email);
        ResponseDto<UserDto> response = new ResponseDto<>(
                OK,
                "User deleted successfully",
                null
        );
        return ResponseEntity.status(OK).body(response);
    }
}
