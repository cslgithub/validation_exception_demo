package com.csl.demo.controller;

import com.csl.demo.entity.User;
import com.csl.demo.service.UserService;
import com.csl.demo.annotation.NotResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author RC
 * @description 用户接口
 */
@RestController
@Api(tags = "用户接口")
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @ApiOperation("获得单个用户")
    @GetMapping("/getUser")
    @NotResponseBody
    public User getUser() {
        User user = new User();
        user.setId(1L);
        user.setAccount("12345678");
        user.setPassword("12345678");
        user.setEmail("123@qq.com");
        return user;
    }

}
