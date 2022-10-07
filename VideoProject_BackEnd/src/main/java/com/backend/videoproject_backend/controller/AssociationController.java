package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.service.AssociationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@Api(tags = "社团管理")
public class AssociationController {

    @Autowired
    public AssociationService associationService;

    @PostMapping("/club")
    @ResponseBody
    @ApiOperation("新建一个社团")
    public String PostClub(String name,@RequestParam(defaultValue ="") String desc)
    {
        try{
            TbAssociationEntity tbAssociationEntity = new TbAssociationEntity();
            tbAssociationEntity.setAssociationName(name);
            tbAssociationEntity.setAssociationDesc(desc);
            tbAssociationEntity.setEstablishTime(new Timestamp(new Date().getTime()));
            associationService.addClub(tbAssociationEntity);
            return "ok";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
