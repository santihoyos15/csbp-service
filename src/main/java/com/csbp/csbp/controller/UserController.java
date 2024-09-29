package com.csbp.csbp.controller;

import com.csbp.csbp.domain.User;
import com.csbp.csbp.dto.AuthRequestDto;
import com.csbp.csbp.dto.ListUsersDto;
import com.csbp.csbp.dto.UserDto;
import com.csbp.csbp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ResponseBody
    public List<User> list(@RequestBody ListUsersDto listUsersDto) {
        return userService.list(listUsersDto);
    }

    @PostMapping()
    public String create(@RequestBody AuthRequestDto authRequestDto) {
        return userService.save(authRequestDto);
    }

    @PutMapping("/{userId}")
    @ResponseBody
    public User edit(@PathVariable Long userId, @RequestBody UserDto userDto) {
        userDto.setId(userId);
        return userService.edit(userDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseBody
    public User delete(@PathVariable Long userId, @RequestBody UserDto userDto) {
        userDto.setId(userId);
        return userService.delete(userDto);
    }
}