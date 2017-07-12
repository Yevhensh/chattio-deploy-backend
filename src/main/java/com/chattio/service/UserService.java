package com.chattio.service;

import com.chattio.dto.UserDto;
import com.chattio.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> findAll(String exceptUsername);

    UserDto findById(long id);

    void createUser(UserDto user);

    User findByEmail(String email);

}
