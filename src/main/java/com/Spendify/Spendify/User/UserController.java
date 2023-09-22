package com.Spendify.Spendify.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user/")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
            this.userService = userService;
    }

    @GetMapping()
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }
}
