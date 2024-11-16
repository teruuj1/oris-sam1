package itis.shuvalova.service;

import itis.shuvalova.dto.UserDto;
import itis.shuvalova.dto.UserRegistrationDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    UserDto get(Integer id);
    UserDto getByLogin(String login);
    void register(UserRegistrationDto user);
}