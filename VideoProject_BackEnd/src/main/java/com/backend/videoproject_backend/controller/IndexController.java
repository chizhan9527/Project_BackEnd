package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.service.UserService;
import com.backend.videoproject_backend.utils.SendSmsUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.backend.videoproject_backend.utils.RandomUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
@Api(tags = "短信服务")
public class IndexController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private SendSmsUtil sendSmsUtil;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    public String sendMessage(@RequestParam String phone){
<<<<<<< HEAD
<<<<<<< HEAD

        System.out.println(phone);
=======
        if(userService.findUserByPhone(phone).isPresent()){
            return "手机号已注册";
        }
>>>>>>> fdf969fdede295c9057c69a60e9f7d412c7add2e
=======
        if(userService.findUserByPhone(phone).isPresent()){
            return "手机号已注册";
        }
>>>>>>> fdf969fdede295c9057c69a60e9f7d412c7add2e
        //生成六位数随机验证码
        String code = RandomUtil.getSixBitRandom();
        //设置redis的key为用户手机号
        String redisKey = phone;
        //发送短信
        boolean isSuccess = sendSmsUtil.sendSms(phone,code);
        if (isSuccess){
            //将这个验证码存入redis中，并设置失效时间为5分钟
            redisTemplate.opsForValue().set(redisKey,code,300, TimeUnit.SECONDS);
            return "success";
        }
        else {
            return "fail";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/checkCode/{phone}/{inputCode}",method = RequestMethod.GET)
    public String checkCode(@PathVariable String phone, @PathVariable String inputCode){
        String redisKey = phone;
        String realCode= (String) redisTemplate.opsForValue().get(redisKey);
        if (realCode!=null && realCode.equals(inputCode)){
            return "success";
        }
        else {
            return "fail";
        }
    }

}

