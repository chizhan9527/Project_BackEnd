package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
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
/*            if (manager_id.equals(0))
                managerService.quitClub(as_id, user_id);
            else {
                if (managerService.ReturnOneMember(as_id, manager_id).getStatus() > 0) {
                    managerService.quitClub(as_id, user_id);
                } else if (managerService.ReturnOneMember(as_id, manager_id).getStatus() == 0)
                    return "权限不够！";
                else
                    return "error";
            }
            Optional<TbAssociationEntity> tbAssociationEntity=AssociationService.findAssociationById(as_id);
            if(tbAssociationEntity.isPresent()) {
                TbAssociationEntity tbAssociationEntity1 = tbAssociationEntity.get();
                tbAssociationEntity1.setMemberNum(tbAssociationEntity1.getMemberNum() - 1);
                AssociationService.updateAssociation(tbAssociationEntity1);
                if (tbAssociationEntity1.getMemberNum() == 0)
                    AssociationService.delClub(as_id);
            }*/

            Optional<TbAssociationEntity> AsE=AssociationService.findAssociationById(as_id);
            Optional<TbUserEntity> UsE=userService.findUserById(user_id);
            Optional<TbUserEntity> MaE=userService.findUserById(manager_id);

            String massage= managerService.DeleteManager(AsE,UsE,MaE);

            if(AsE.isPresent()) {
                TbAssociationEntity tbAssociationEntity1 = AsE.get();
                tbAssociationEntity1.setMemberNum(tbAssociationEntity1.getMemberNum() - 1);
                AssociationService.updateAssociation(tbAssociationEntity1);
                if (tbAssociationEntity1.getMemberNum() == 0)
                    AssociationService.delClub(as_id);
            }
            return massage;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/manager")
    @ResponseBody
    @ApiOperation("添加一个关联表（加入社团）")
    public String AddManager(Integer as_id,Integer user_id) {
        try {
            // TbManagerEntity tbManagerEntity = new TbManagerEntity();
            // tbManagerEntity.setAsId(AssociationService.findAssociationById(as_id).get().getId());
            // tbManagerEntity.setUserId(user_id);
            // tbManagerEntity.setJoinTime(new Timestamp(new Date().getTime()));
            // tbManagerEntity.setStatus(0);
            // managerService.joinClub(tbManagerEntity);
            // Optional<TbAssociationEntity> tbAssociationEntity = AssociationService.findAssociationById(as_id);
            // if (tbAssociationEntity.isPresent()) {
            //     TbAssociationEntity tbAssociationEntity1 = tbAssociationEntity.get();
            //     tbAssociationEntity1.setMemberNum(tbAssociationEntity1.getMemberNum() + 1);
            //     AssociationService.updateAssociation(tbAssociationEntity1);
            // }
            // return ("OK");
            Optional<TbAssociationEntity> AsE=AssociationService.findAssociationById(as_id);
            Optional<TbUserEntity> UsE=userService.findUserById(user_id);

            String massage= managerService.joinClub(AsE,UsE);

            if (AsE.isPresent()) {
                 TbAssociationEntity tbAssociationEntity1 = AsE.get();
                 tbAssociationEntity1.setMemberNum(tbAssociationEntity1.getMemberNum() + 1);
                 AssociationService.updateAssociation(tbAssociationEntity1);
            }

            return massage;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/manager/{as_id}/{rank}")
    @ResponseBody
    @ApiOperation("查询社团指定成员，0为所有成员，1为管理员和社长，2为社长")
    public List<TbUserEntity> GetTargetMember(@PathVariable Integer as_id,@PathVariable Integer rank){
        try{
/*
            List<TbUserEntity> tbUserEntities=new ArrayList<>();
        List<TbManagerEntity> ClubMember=managerService.ReturnAllMember(as_id);
        if(ClubMember.isEmpty())
            return null;
        else {
            Iterator<TbManagerEntity> iterator = ClubMember.listIterator();
            while (iterator.hasNext()) {
                TbManagerEntity tbManagerEntity = iterator.next();
                if (tbManagerEntity.getStatus() >= rank) {
                    TbUserEntity tbUserEntity=userService.findUserById(tbManagerEntity.getUserId()).get();
                    tbUserEntity.setRank(tbManagerEntity.getStatus());
                    tbUserEntities.add(tbUserEntity);
                }
            }
            return tbUserEntities;
*/
            Optional<TbAssociationEntity> AsE=AssociationService.findAssociationById(as_id);

            List<Integer> us_ids = managerService.ReturnTragetMembers(AsE,rank);

            List<TbUserEntity> tbUserEntities=new ArrayList<>();

            if (us_ids.isEmpty()){
                System.out.println("GetTargetMember False! No users!");
                return tbUserEntities;
            }

            for (Integer us_id : us_ids){
                TbUserEntity tbUserEntity=userService.findUserById(us_id).get();

                tbUserEntities.add(tbUserEntity);
            }

            System.out.println("GetTargetMember Success!");

            return tbUserEntities;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getClub/{user_id}")
    @ResponseBody
    @ApiOperation("返回关注的社团")
    public List<TbAssociationEntity> FindJoinedClub(@PathVariable Integer user_id)
    {
        try {
            if(user_id<1||user_id>100000000)
            {
                System.out.println("No such user");
                return null;
            }
            Optional<TbUserEntity> UsE=userService.findUserById(user_id);

            List<TbAssociationEntity> tbAssociationEntities=new ArrayList<>();
            List<TbManagerEntity> tbManagerEntities= managerService.ReturnJoinedClub( UsE);
            if(tbManagerEntities.isEmpty()){

                System.out.println("FindJoinedClub False! No such User or User didn't join clubs ");

                return null;
            }
            else {
                Iterator<TbManagerEntity> iterator = tbManagerEntities.listIterator();
                while (iterator.hasNext()) {
                    TbManagerEntity tbManagerEntity = iterator.next();
                    tbAssociationEntities.add(AssociationService.findAssociationById(tbManagerEntity.getAsId()).get());
                }
                System.out.println("FindJoinedClub Success!");
                return tbAssociationEntities;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getRank/{user_id}/{as_id}")
    @ResponseBody
    @ApiOperation("返回特定成员在特定社团下的权限")
    public Integer SearchClub(@PathVariable Integer user_id,@PathVariable Integer as_id)
    {
        try {
            Optional<TbAssociationEntity> AsE=AssociationService.findAssociationById(as_id);
            Optional<TbUserEntity> UsE=userService.findUserById(user_id);

            Optional<TbManagerEntity> tbManagerEntityOptional=managerService.ReturnOneMember(AsE,UsE);
            if (tbManagerEntityOptional.isEmpty())
                return -1;
            return tbManagerEntityOptional.get().getStatus();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/changeRank")
    @ResponseBody
    @ApiOperation("更改成员的权限，如果目标是管理员则降级，如果是成员则升为管理员")
    public String ChangeRank( Integer user_id, Integer as_id,Integer manager_id)
    {
        try {
/*
            TbManagerEntity tbManagerEntity=managerService.ReturnOneMember(as_id,user_id);
            Integer rank= tbManagerEntity.getStatus();
            if(rank==1)//为管理员，降级
            {
                tbManagerEntity.setStatus(0);
                managerService.updateManager(tbManagerEntity);
            }
            else if(rank==0)//为用户，升级
            {
                tbManagerEntity.setStatus(1);
                managerService.updateManager(tbManagerEntity);
            }
            return "ok";
*/
            Optional<TbAssociationEntity> AsE=AssociationService.findAssociationById(as_id);
            Optional<TbUserEntity> UsE=userService.findUserById(user_id);
            Optional<TbUserEntity> MaE=userService.findUserById(manager_id);

            String massage= managerService.ChangeRank(AsE,UsE,MaE);

            if(AsE.isPresent()) {
                TbAssociationEntity tbAssociationEntity1 = AsE.get();
                tbAssociationEntity1.setMemberNum(tbAssociationEntity1.getMemberNum() - 1);
                //AssociationService.updateAssociation(tbAssociationEntity1);
                //if (tbAssociationEntity1.getMemberNum() == 0)
                    //AssociationService.delClub(as_id);
            }
            return massage;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
