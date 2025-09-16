package org.angulo.userservice.constant;

import com.github.javafaker.Faker;
import org.angulo.userservice.model.User;
import org.angulo.userservice.model.dto.UserDto;

import java.time.LocalDate;
import java.util.List;

public class Data {

    private static Faker faker = new Faker();

    public static List<UserDto> users(){
        return List.of(
                new UserDto("name1","lastname1","user1@correo.com","123", null, "address1"),
                new UserDto("name2","lastname2","user2@correo.com","123", null, "address2"),
                new UserDto("name3","lastname3","user3@correo.com","123", null, "address3")

        );
    }

    public static UserDto user(){
        return new UserDto(
                "name1",
                "lastname1",
                "user1@correo.com",
                "123",
                null,
                "address1");
    }

    public static User userDb(){
        return new User(
                "name",
                "lastname",
                "user@email.com",
                "1234567890",
                null,
                "address"
        );
    }

    public static List<User> userList(){
        return List.of(
                new User("name-1","lastname-1","user-1@email.com","1234567890",LocalDate.now(), faker.address().fullAddress()),
                new User(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),faker.phoneNumber().cellPhone(),LocalDate.now(),faker.address().fullAddress()),
                new User(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),faker.phoneNumber().cellPhone(),LocalDate.now(),faker.address().fullAddress()),
                new User(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),faker.phoneNumber().cellPhone(),LocalDate.now(),faker.address().fullAddress()),
                new User(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),faker.phoneNumber().cellPhone(),LocalDate.now(),faker.address().fullAddress())
        );
    }
}
