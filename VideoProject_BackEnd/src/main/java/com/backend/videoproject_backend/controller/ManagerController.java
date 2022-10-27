package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbManagerEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.AssociationService;
import com.backend.videoproject_backend.service.ManagerService;
import com.backend.videoproject_backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@Controller
@Api(tags = "入社管理")
public class ManagerController {
    @Autowired
    public ManagerService managerService;

    @Autowired
    public UserService userService;
    @Autowired
    public AssociationService AssociationService;

    @DeleteMapping("/manager")
    @ResponseBody
    @ApiOperation("删除一个关联表（退出社团或管理员、社长踢人）")
    public String DeleteManager(Integer as_id,Integer user_id,Integer manager_id) {
        try {
            if (manager_id.equals(0))
                managerService.quitClub(as_id, user_id);
            else {
                if (managerService.ReturnOneMember(as_id, manager_id).getStatus() > 0) {
                    managerService.quitClub(as_id, user_id);
                } else if (managerService.ReturnOneMember(as_id, manager_id).getStatus() == 0)
                    return "权限不够！";
                else
                    return "error";
            }
            return ("OK");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/manager")
    @ResponseBody
    @ApiOperation("添加一个关联表（加入社团）")
    public String AddManager(Integer as_id,Integer user_id) {
        try{
        TbManagerEntity tbManagerEntity=new TbManagerEntity();
        tbManagerEntity.setAsId(AssociationService.findAssociationById(as_id).get().getId());
        tbManagerEntity.setUserId(user_id);
        tbManagerEntity.setJoinTime(new Timestamp(new Date().getTime()));
        tbManagerEntity.setStatus(0);
        managerService.joinClub(tbManagerEntity);
        return("OK");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/manager")
    @ResponseBody
    @ApiOperation("查询社团指定成员，0为所有成员，1为管理员和社长，2为社长")
    public List<TbUserEntity> GetTargetMember(Integer as_id,Integer rank){
        try{
        List<TbUserEntity> tbUserEntities=new ArrayList<>();
        List<TbManagerEntity> ClubMember=managerService.ReturnAllMember(as_id);
        if(ClubMember.isEmpty())
            return null;
        else {
            Iterator<TbManagerEntity> iterator = ClubMember.listIterator();
            while (iterator.hasNext()) {
                TbManagerEntity tbManagerEntity=iterator.next();
                if (tbManagerEntity.getStatus() >= rank)
                    tbUserEntities.add(userService.findUserById(tbManagerEntity.getUserId()).get());
            }
            return tbUserEntities;
        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
