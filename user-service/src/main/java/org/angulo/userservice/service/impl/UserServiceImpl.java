package org.angulo.userservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.angulo.userservice.Exception.UserNotFoundException;
import org.angulo.userservice.model.User;
import org.angulo.userservice.model.dto.UserDto;
import org.angulo.userservice.repository.IUserRepository;
import org.angulo.userservice.service.IUserService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public UserDto save(UserDto userDto) {
        User user = new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.getDateOfBirth(),
                userDto.getAddress()
        );
        userRepository.save(user);
        return userDto;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserDto update(UserDto userDto, UUID id) {
        User user = new User(
                id,
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.getDateOfBirth(),
                userDto.getAddress()
        );
        userRepository.save(user);
        return userDto;
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.removeUserByEmail(email);
    }
}
