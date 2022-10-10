package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.backend.videoproject_backend.utils.MD5Util.encrypt;


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
            tbUserEntity.setPassword(encrypt(password));
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

    @GetMapping("/user")
    @ResponseBody
    @ApiOperation("查询所有用户信息")
    public List<TbUserEntity> FindAllUser()
    {
        try {
            return userService.findAllUser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    @ApiOperation("查询一名用户信息")
    public Optional<TbUserEntity> FindOneUser(@PathVariable Integer id)
    {
        try {
            return userService.findUserById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    @ApiOperation("更新一名用户信息")
    public String UpdateUser(@PathVariable Integer id,String name,String phone,String detail,String password,String birthday,@RequestParam(defaultValue = "0") String avatar,Integer gender,String email)
    {
        try {
            Optional<TbUserEntity> tbUserEntity = userService.findUserById(id);
            if(tbUserEntity.isPresent()) {
                tbUserEntity.get().setName(name);
                tbUserEntity.get().setPhone(phone);
                tbUserEntity.get().setDetail(detail);
                tbUserEntity.get().setPassword(encrypt(password));
                tbUserEntity.get().setBirthday(birthday);
                tbUserEntity.get().setAvator(avatar);
                tbUserEntity.get().setGender(gender);
                tbUserEntity.get().setEmail(email);
                userService.updateUser(tbUserEntity.get());
                return "ok";
            }
            else {
                return "error";
            }
    }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
