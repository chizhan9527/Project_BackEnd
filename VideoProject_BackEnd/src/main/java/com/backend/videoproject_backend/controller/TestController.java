package com.backend.videoproject_backend.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.backend.videoproject_backend.dto.Test;
import com.backend.videoproject_backend.service.TestService;
import com.backend.videoproject_backend.utils.SendSmsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Api(tags = "测试喵")
@RequestMapping("/test/")
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private SendSmsUtil sendSmsUtil;

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

    @DeleteMapping("/del{id}")
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

    @PostMapping ("/fageduanxin")
    @ResponseBody
    public String send(@RequestParam String phone,@RequestParam String code)
    {
        sendSmsUtil.sendSms(phone,code);
        return "ok";
    }

    // 测试登录，浏览器访问： http://localhost:8081/test/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/test/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
