package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dao.AssociationDao;
import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbManagerEntity;
import com.backend.videoproject_backend.service.AssociationService;
import com.backend.videoproject_backend.service.ManagerService;
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
    @Autowired
    public ManagerService managerService;
    @Autowired
    public AssociationDao associationDao;

    @PostMapping("/club")
    @ResponseBody
    @ApiOperation("新建一个社团")
    public String PostClub(String name,@RequestParam(defaultValue ="") String desc,Integer user_id)
    {
        try{
            //创建社团
            TbAssociationEntity tbAssociationEntity = new TbAssociationEntity();
            tbAssociationEntity.setAssociationName(name);
            tbAssociationEntity.setAssociationDesc(desc);
            tbAssociationEntity.setEstablishTime(new Timestamp(new Date().getTime()));
            tbAssociationEntity.setMemberNum(1);
            associationService.addClub(tbAssociationEntity);
            //创建社长的关联表
            TbManagerEntity tbManagerEntity=new TbManagerEntity();
            tbManagerEntity.setAsId(tbAssociationEntity.getId());
            tbManagerEntity.setUserId(user_id);
            tbManagerEntity.setJoinTime(tbAssociationEntity.getEstablishTime());
            tbManagerEntity.setStatus(2);
            managerService.joinClub(tbManagerEntity);
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

    @PostMapping("/clubInfo")
    @ResponseBody
    @ApiOperation("更改社团信息")
    public String ChangeInfo(Integer id,String type,String info)
    {
        try{
            Optional<TbAssociationEntity> tbAssociationEntity=associationService.findAssociationById(id);
            if(tbAssociationEntity.isPresent()) {
                TbAssociationEntity tbAssociationEntity1=tbAssociationEntity.get();
                switch (type)
                {
                    case "name":
                        tbAssociationEntity1.setAssociationName(info);
                        break;
                    case "desc":
                        tbAssociationEntity1.setAssociationDesc(info);
                        break;
                    default:
                        return "error";
                }
                associationService.updateAssociation(tbAssociationEntity1);
                return "ok";
            }
            else {
                return "error";
            }
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

    @GetMapping("/SearchClub/{name}")
    @ResponseBody
    @ApiOperation("搜索社团")
    public List<TbAssociationEntity> SearchClub(@PathVariable String name)
    {
        try {
            return associationDao.findByAssociationNameLike("%"+name+"%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
