package itis.shuvalova.dao;

import itis.shuvalova.entity.User;

import java.util.List;

public interface UserDao {

    User get(Integer id);

    User getByLogin(String login);

    List<User> getAll();

    void save(User user);

}