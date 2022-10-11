package com.backend.videoproject_backend.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.backend.videoproject_backend.utils.MD5Util.encrypt;

@RestController
@Api(tags ="登录服务")
public class LoginController {

    @Autowired
    private UserService userService;

    // 登录
    @PostMapping("/Login")
    @ResponseBody
    @ApiOperation("登录")
    public SaResult doLogin(String phone, String password)
    {
        Optional<TbUserEntity> tbUserEntity = userService.findUserByPhone(phone);
        if(tbUserEntity.isPresent()) {
            if(tbUserEntity.get().getPassword().equals(encrypt(password))){
                StpUtil.login(tbUserEntity.get().getId());
                SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
                return SaResult.data(saTokenInfo);
            }
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态
    @GetMapping ("/Login")
    @ResponseBody
    @ApiOperation("查询登录状态")
    public SaResult isLogin()
    {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息
    @GetMapping("/token")
    @ResponseBody
    @ApiOperation("查询 Token 信息")
    public SaResult tokenInfo()
    {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 注销
    @PostMapping("/logout")
    @ResponseBody
    @ApiOperation("注销")
    public SaResult logout()
    {
        StpUtil.logout();
        return SaResult.ok();
    }

}

