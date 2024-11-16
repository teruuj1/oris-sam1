package itis.shuvalova.service.impl;

import itis.shuvalova.dao.UserDao;
import itis.shuvalova.dao.impl.UserDaoImpl;
import itis.shuvalova.dto.UserDto;
import itis.shuvalova.dto.UserRegistrationDto;
import itis.shuvalova.entity.User;
import itis.shuvalova.service.UserService;
import itis.shuvalova.util.PasswordUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();
    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(
                u -> new UserDto(u.getName(), null, u.getLastName())
        ).collect(Collectors.toList());
    }
    @Override
    public UserDto get(Integer id) {
        return null;
    }
    @Override
    public UserDto getByLogin(String login) {
        User u = userDao.getByLogin(login);
        return new UserDto(u.getName(), null, u.getLastName());
    }
    @Override
    public void register(UserRegistrationDto user) {
        userDao.save(new User(
                1,
                user.getName(),
                user.getLastname(),
                user.getLogin(),
                PasswordUtil.encrypt(user.getPassword())
        ));
    }
}