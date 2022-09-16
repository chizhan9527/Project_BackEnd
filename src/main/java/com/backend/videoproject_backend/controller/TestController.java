package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.Test;
import com.backend.videoproject_backend.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author 展驰
 * @version 1.0
 * 2022/09/15
 */
@Controller
@Api(tags = "用户管理")
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping ("/save")
    @ResponseBody
    @ApiOperation("保存测试用户")
    public String saveTest()
    {
        Test test = new Test();
        test.setName("zc");
        test.setPassword("123");
        test.setEmail("zc@qq.com");
        test.setBirthday(new Date());
        testService.addTest(test);
        return "success";
    }

    @DeleteMapping("/del/{id}")
    @ResponseBody
    public String delTest(@PathVariable Integer id)
    {
        testService.delTest(id);
        return"ok";
    }

    @PutMapping("/update{email}")
    @ResponseBody
    public String updateTest(@PathVariable String email)
    {
        testService.updateTest(email);
        return"ok";
    }
}
