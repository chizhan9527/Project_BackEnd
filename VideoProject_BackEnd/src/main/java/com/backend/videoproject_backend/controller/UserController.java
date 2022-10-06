package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;


@RestController
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/user")
    @ResponseBody
    @ApiOperation("新建一个用户信息")
    public String PostUser(String name,String phone,String password,@RequestParam(defaultValue = "0") String avatar)
    {
        try{
            TbUserEntity tbUserEntity = new TbUserEntity();
            tbUserEntity.setName(name);
            tbUserEntity.setPhone(phone);
            tbUserEntity.setPassword(password);
            tbUserEntity.setAvator(avatar);
            tbUserEntity.setCreateTime(new Timestamp(new Date().getTime()));
            userService.addUser(tbUserEntity);
            return "ok";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    @ApiOperation("删除一个用户信息")
    public String DeleteUser(@PathVariable Integer id)
    {
        try {
            userService.deleteUser(id);
            return "ok";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
