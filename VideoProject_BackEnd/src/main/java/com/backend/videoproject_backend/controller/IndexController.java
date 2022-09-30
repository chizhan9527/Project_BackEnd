package com.backend.videoproject_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
public class IndexController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/sendmessage",method = RequestMethod.GET)
    public String sendmessage(){
        //这里的userId在真实业务中通过个人身份的令牌获取，这里直接仿造一个
        String userId="1";
        //生成六位数随机验证码
        String code=getCode();
        //设置redis的key，这里设置为项目名:使用的字段:用户Id
        String redisKey="VERIFATIONCODE:CODE:"+userId;
        //将这个验证码存入redis中，并设置失效时间为5分钟
        redisTemplate.opsForValue().set(redisKey,code,300, TimeUnit.SECONDS);
        //发送短信
        boolean isSuccess=send(code);
        if (isSuccess){
            return "success";
        }else {
            return "fail";
        }
    }

    private boolean send(String code) {
        String msg="验证码为："+code+"，验证码有效期5分钟，请及时验证";
        System.out.println(msg);
        return true;
    }

    //生成六位随机验证码
    private static String getCode() {
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result += random.nextInt(10);
        }
        return result;
    }

}

