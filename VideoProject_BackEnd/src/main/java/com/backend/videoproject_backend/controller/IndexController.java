package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.utils.SendSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.backend.videoproject_backend.utils.RandomUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
public class IndexController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private SendSmsUtil sendSmsUtil;

    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/sendmessage",method = RequestMethod.POST)
    public String sendmessage(@RequestParam String phone){
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
    @RequestMapping(value = "/checkCode",method = RequestMethod.GET)
    public String checkCode(@RequestParam String phone,@RequestParam String inputCode){
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

