package org.angulo.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.angulo.userservice.Exception.UserNotFoundException;
import org.angulo.userservice.constant.Data;
import org.angulo.userservice.model.User;
import org.angulo.userservice.model.dto.ResponseDto;
import org.angulo.userservice.model.dto.UserDto;
import org.angulo.userservice.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IUserService userService;

    @Captor
    ArgumentCaptor<?> captor;

    ObjectMapper mapper;

    @BeforeEach
    void beforeTest() {
        mapper = new ObjectMapper();
    }

    @DisplayName("Crud Successfully Test")
    @Nested
    class crudSucccesfullTests {

        @Test
        void testFindAll() throws Exception {

            List<UserDto> users = Data.users();
            ResponseDto<List<UserDto>> response = new ResponseDto<>(
                    HttpStatus.OK,
                    "All users registered",
                    users
            );
            when(userService.findAllUsers()).thenReturn(users);

            mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.httpStatus").value("OK")) // <-- campo dentro de ResponseDto
                    .andExpect(jsonPath("$.message").value("All users registered"))
                    .andExpect(jsonPath("$.data[0].firstName").value("name1"))
                    .andExpect(jsonPath("$.data[1].firstName").value("name2"))
                    .andExpect(jsonPath("$.data[2].firstName").value("name3"))
            ;

            verify(userService).findAllUsers();
        }

        @Test
        void testUserByEmail() throws Exception {
            UserDto user = Data.user();
            String email = "test@correo.com";
            user.setEmail(email);
            when(userService.findUserByEmail(anyString())).thenReturn(user);

            mockMvc.perform(get("/users/{email}", email).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.httpStatus").value("OK"))
                    .andExpect(jsonPath("$.message").value("User by email"))
                    .andExpect(jsonPath("$.data.email").value(email))
            ;
            verify(userService).findUserByEmail(email);
        }

        @Test
        void testCreateUser() throws Exception {
            UserDto newUser = Data.user();
            when(userService.save(any(UserDto.class))).thenReturn(newUser);

            mockMvc.perform(
                            post("/users")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(mapper.writeValueAsString(newUser)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.httpStatus").value("CREATED"))
                    .andExpect(jsonPath("$.message").value("User created successfully"))
                    .andExpect(jsonPath("$.data").value(newUser))
            ;
            verify(userService).save(any(UserDto.class));
        }

        @Test
        void testUpdateUser() throws Exception {
            UUID id = UUID.randomUUID();
            UserDto userToUpdate = Data.user();
            UserDto updatedUser = Data.user();
            updatedUser.setFirstName("UpdatedName");

            when(userService.update(any(UserDto.class), eq(id))).thenReturn(updatedUser);

            mockMvc.perform(put("/users")
                            .param("id", id.toString())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(userToUpdate)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.httpStatus").value("OK"))
                    .andExpect(jsonPath("$.message").value("User updated successfully"))
                    .andExpect(jsonPath("$.data.firstName").value("UpdatedName"));

            verify(userService).update(any(UserDto.class), eq(id));
        }

        @Test
        void testDeleteUser() throws Exception {
            String email = "test@email.com";
            doNothing().when(userService).deleteByEmail(email);

            mockMvc.perform(delete("/users")
                            .param("email", email)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.httpStatus").value("OK"))
                    .andExpect(jsonPath("$.message").value("User deleted successfully"))
                    .andExpect(jsonPath("$.data").doesNotExist())
            ;

            verify(userService).deleteByEmail(email);
        }
    }

    // TDD
    @DisplayName("Crud Exception Handle Tests")
    @Nested
    class crudExceptionHandleTests {
        @Test
        void testUserByEmail_NotFound() throws Exception {
            String email = "missing@correo.com";
            when(userService.findUserByEmail(email))
                    .thenThrow(new UserNotFoundException("User not found")); // luego cambias a UserNotFoundException

            mockMvc.perform(get("/users/{email}", email)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.httpStatus").value("NOT_FOUND"))
                    .andExpect(jsonPath("$.message").value("User not found"))
                    .andExpect(jsonPath("$.data").doesNotExist());
        }

        @Test
        void testFindAll_GenericException() throws Exception {
            when(userService.findAllUsers())
                    .thenThrow(new RuntimeException("Unexpected error"));

            mockMvc.perform(get("/users")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.httpStatus").value("INTERNAL_SERVER_ERROR"))
                    .andExpect(jsonPath("$.message").value("Internal server error"))
                    .andExpect(jsonPath("$.data").doesNotExist());
        }
    }

}