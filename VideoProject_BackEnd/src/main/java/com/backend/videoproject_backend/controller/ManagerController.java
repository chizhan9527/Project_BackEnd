package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbManagerEntity;
import com.backend.videoproject_backend.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "入社管理")
public class ManagerController {
    /*
    @Autowired
    public ManagerService managerService;

    @PostMapping("/manager")
    @ResponseBody
    @ApiOperation("新建一个关联表")
    public String PostManager(String name, @RequestParam(defaultValue ="") String desc, Integer user_id)
    {
        TbManagerEntity tbManagerEntity=new TbManagerEntity();
        tbManagerEntity.setAsId(tbAssociationEntity.getId());
        tbManagerEntity.setUserId(user_id);
        tbManagerEntity.setJoinTime(tbAssociationEntity.getEstablishTime());
        tbManagerEntity.setStatus(2);
        managerService.joinClub(tbManagerEntity);
         */
}
