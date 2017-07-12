package com.chattio.service.impl;

import com.chattio.dto.RoleDto;
import com.chattio.dto.UserDto;
import com.chattio.entity.User;
import com.chattio.repository.UserRepository;
import com.chattio.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll(String exceptUsername) {
        return userRepository.findAll(exceptUsername)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(long id) {
        return modelMapper.map(userRepository.findById(id), UserDto.class);
    }

    @Override
    public void createUser(UserDto userDto) {
        userDto.setRoles(Arrays.asList(RoleDto.USER));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.create(modelMapper.map(userDto, User.class));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
