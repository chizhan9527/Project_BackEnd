package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.service.AssociationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    };

    @DeleteMapping("/club/{id}")
    @ResponseBody
    @ApiOperation("删除一个社团")
    public String DeleteClub(@PathVariable Integer id)
    {
        try{
            associationService.delClub(id);
            return "ok";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/club/{id}")
    @ResponseBody
    @ApiOperation("更改社团信息")
    public String ChangeInfo(@PathVariable Integer id,String type,String info)
    {
        try{
            Optional<TbAssociationEntity> tbAssociationEntity=associationService.findAssociationById(id);
            if(type.equals("name"))
            {
                tbAssociationEntity.get().setAssociationName(info);
            }
            else if(type.equals("desc"))
            {
                tbAssociationEntity.get().setAssociationDesc(info);
            }
            associationService.updateAssociation(tbAssociationEntity.get());
            return "ok";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/club/{id}")
    @ResponseBody
    @ApiOperation("查询一个社团")
    public Optional<TbAssociationEntity> FindOneClub(@PathVariable Integer id)
    {
        try {
            return associationService.findAssociationById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/club")
    @ResponseBody
    @ApiOperation("返回所有社团")
    public List<TbAssociationEntity> FindAllClub()
    {
        try {
            return associationService.findAllClub();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
