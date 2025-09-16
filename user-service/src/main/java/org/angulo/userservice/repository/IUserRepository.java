package org.angulo.userservice.repository;

import org.angulo.userservice.model.User;
import org.angulo.userservice.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {

    /* CRUD */

    Optional<User> findUserByPhone(String phone);

    @Query("SELECT new org.angulo.userservice.model.dto.UserDto(u.firstName, u.lastName, u.email, u.phone, u.dateOfBirth, u.address) FROM User u")
    List<UserDto> findAllUsers();

    @Query("SELECT new org.angulo.userservice.model.dto.UserDto(u.firstName, u.lastName, u.email, u.phone, u.dateOfBirth, u.address) FROM User u WHERE u.email = :email")
    Optional<UserDto> findUserByEmail(@Param("email") String email);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    Optional<UUID> findIdUserByEmail(@Param("email") String email);

    // DELETE
    void removeUserByEmail(String email);


}
