package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dao.FeedbackDao;
import com.backend.videoproject_backend.dao.PhysicalDao;
import com.backend.videoproject_backend.dto.TbFeedbackEntity;
import com.backend.videoproject_backend.dto.TbPhysicalEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

import static com.backend.videoproject_backend.utils.MD5Util.encrypt;

@RestController
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public FeedbackDao feedbackDao;

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

    @PutMapping("/user")
    @ResponseBody
    @ApiOperation("更新一名用户信息")
    public String UpdateUser(@RequestBody TbUserEntity user)
    {
        try {
            Optional<TbUserEntity> target = userService.findUserById(user.getId());
            if(target.isPresent()) {
                BeanUtils.copyProperties(user,target.get(),getNullPropertyNames(user));
                userService.updateUser(target.get());
                return "ok";
            }
            else {
                return "error";
            }
    }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @PostMapping("/physical")
    @ResponseBody
    @ApiOperation("修改一个用户身体数据")
    public String postPhysical(Integer id,int height,int weight,double bmi,int bust,int waist,int hipline)
    {
        try {
            Optional<TbPhysicalEntity> tbPhysicalEntity = userService.findPhysicalByUserId(id);
            if(tbPhysicalEntity.isPresent()){
                tbPhysicalEntity.get().setHeight(height);
                tbPhysicalEntity.get().setWeight(weight);
                tbPhysicalEntity.get().setBmi(bmi);
                tbPhysicalEntity.get().setBust(bust);
                tbPhysicalEntity.get().setWaist(waist);
                tbPhysicalEntity.get().setHipline(hipline);
                tbPhysicalEntity.get().setModificationTime(new Timestamp(new Date().getTime()));
                userService.addPhysical(tbPhysicalEntity.get());
                return "修改用户信息";
            }
            else{
                TbPhysicalEntity target = new TbPhysicalEntity();
                target.setHeight(height);
                target.setWeight(weight);
                target.setWaist(waist);
                target.setBmi(bmi);
                target.setBust(bust);
                target.setUserId(id);
                target.setHipline(hipline);
                target.setModificationTime(new Timestamp(new Date().getTime()));
                userService.addPhysical(target);
                return "新建用户身体信息";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/physical/{id}")
    @ResponseBody
    @ApiOperation("查询一名用户身体数据")
    public Optional<TbPhysicalEntity> FindPhysical(@PathVariable Integer id)
    {
        try {
            return userService.findPhysicalByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/feedback")
    @ResponseBody
    @ApiOperation("创建一个用户反馈")
    public String postFeedback(Integer id,String content)
    {
        try{
            TbFeedbackEntity tbFeedbackEntity = new TbFeedbackEntity();
            tbFeedbackEntity.setContent(content);
            tbFeedbackEntity.setUserId(id);
            tbFeedbackEntity.setCreateTime(new Timestamp(new Date().getTime()));
            userService.postFeedback(tbFeedbackEntity);
            return "ok";
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
