package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.Test;
import com.backend.videoproject_backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author 展驰
 * @version 1.0
 * 2022/09/15
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/save")
    @ResponseBody
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

    @RequestMapping("/del/{id}")
    @ResponseBody
    public String delTest(@PathVariable Integer id)
    {
        testService.delTest(id);
        return"ok";
    }
    @RequestMapping("/update{email}")
    @ResponseBody
    public String updateTest(@PathVariable String email)
    {

        testService.updateTest(email);
        return"ok";
    }
}
