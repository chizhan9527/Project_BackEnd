package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.AssociationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

public class AssociationController {
    public AssociationService clubService;
    @PostMapping("/club")
    @ResponseBody
    @ApiOperation("新建一个社团")
    public String PostUser(String name,@RequestParam(defaultValue ="0") String desc)
    {
        try{
            TbAssociationEntity tbClubEntity = new TbAssociationEntity();
            tbClubEntity.setAssociationName(name);
            if(!desc.equals("0")) {
                tbClubEntity.setAssociationDesc(desc);
            }
            tbClubEntity.setEstablishTime(new Timestamp(new Date().getTime()));
            clubService.addClub(tbClubEntity);
            return "ok";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
