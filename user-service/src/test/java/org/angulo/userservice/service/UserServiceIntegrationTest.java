package org.angulo.userservice.service;

import com.github.javafaker.Faker;
import org.angulo.userservice.Exception.UserNotFoundException;
import org.angulo.userservice.constant.Data;
import org.angulo.userservice.model.User;
import org.angulo.userservice.model.dto.UserDto;
import org.angulo.userservice.repository.IUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserService userService;

    private Faker faker;

    @BeforeEach
    void setUp() {
        List<User> users = Data.userList();
        userRepository.saveAll(users);
        faker = new Faker();
    }

    @Test
    void saveUserTest() {
        UserDto newUser = new UserDto("New", "User", "newuser@test.com", "55555",
                LocalDate.of(2000, 1, 1), "New Address");

        UserDto saved = userService.save(newUser);

        assertEquals("New", saved.getFirstName());
        assertEquals("newuser@test.com", saved.getEmail());

        UserDto fromDb = userService.findUserByEmail("newuser@test.com");
        assertEquals("New", fromDb.getFirstName());
    }

    @Test
    void findAllUsersTest() {
        List<UserDto> userList = userService.findAllUsers();
        assertEquals(5,userList.size());
    }

    @Test
    void findUserByEmail() {
        UserDto userDto = userService.findUserByEmail("user-1@email.com");

        assertEquals("name-1",userDto.getFirstName());
        assertEquals("lastname-1",userDto.getLastName());
        assertEquals("1234567890",userDto.getPhone());
        assertEquals("user-1@email.com",userDto.getEmail());
    }

    @Test
    void updateUserTest() {
        User user = userRepository.findUserByPhone("1234567890").orElseThrow();

        assertEquals("name-1",user.getFirstName());
        assertEquals("lastname-1",user.getLastName());
        assertEquals("1234567890",user.getPhone());
        assertEquals("user-1@email.com",user.getEmail());

        UserDto userUpdated = new UserDto(
            faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                LocalDate.now(),
                faker.address().fullAddress()
        );

        userService.update(userUpdated,user.getId());

        UserDto userUpdatedDb = userService.findUserByEmail(userUpdated.getEmail());

        assertEquals(userUpdated.getFirstName(),userUpdatedDb.getFirstName());
        assertEquals(userUpdated.getLastName(),userUpdatedDb.getLastName());
        assertEquals(userUpdated.getPhone(),userUpdatedDb.getPhone());
        assertEquals(userUpdated.getEmail(),userUpdatedDb.getEmail());
        assertEquals(userUpdated.getDateOfBirth(),userUpdatedDb.getDateOfBirth());
        assertEquals(userUpdated.getAddress(),userUpdatedDb.getAddress());
    }

    @Test
    void deleteUserTest() {
        String email = "user-1@email.com";
        userService.deleteByEmail(email);
        assertThrows(UserNotFoundException.class,
                () -> userService.findUserByEmail(email));
    }

    @Test
    void findUserTest_NotFound() {
        String missingEmail = "missing@email.com";
        assertThrows(UserNotFoundException.class, () -> userService.findUserByEmail(missingEmail));
    }
}
