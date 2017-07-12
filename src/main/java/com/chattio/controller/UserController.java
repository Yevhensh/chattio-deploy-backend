package com.chattio.controller;

import com.chattio.dto.UserDto;
import com.chattio.service.DialogService;
import com.chattio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DialogService dialogService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers(Principal principal) {
        return userService.findAll(principal.getName());
    }

    @GetMapping("/users/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @GetMapping("/users/dialogs/{dialogId}")
    public List<UserDto> getUnassociatedDialogUsers(@PathVariable Long dialogId) {
        return dialogService.getUnassociatedDialogUsers(dialogId);
    }
}
