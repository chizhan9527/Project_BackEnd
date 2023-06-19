package com.backend.videoproject_backend.service.impl;

import com.backend.videoproject_backend.dao.AssociationDao;
import com.backend.videoproject_backend.dao.ManagerDao;
import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbManagerEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.*;

@Service
public class ManagerServiceImpl implements ManagerService  {

    @Autowired
    private ManagerDao managerDao;
    @Override
    public String joinClub(Optional<TbAssociationEntity> associationEntityOptional, Optional<TbUserEntity> userEntityOptional){
        try{
            String message="";

            if(associationEntityOptional.isEmpty()||userEntityOptional.isEmpty()) {
                if (associationEntityOptional.isEmpty())
                    message += "No such association ";

                if (userEntityOptional.isEmpty())
                    message +=  "No such User ";

                return message;
            }
            if(managerDao.findByAsIdAndUserId(associationEntityOptional.get().getId(),userEntityOptional.get().getId())!=null)
            {
                message +=  "User has joined";
                return message;
            }
            TbManagerEntity tbManagerEntity = new TbManagerEntity();
            tbManagerEntity.setAsId(associationEntityOptional.get().getId());
            tbManagerEntity.setUserId(userEntityOptional.get().getId());
            tbManagerEntity.setJoinTime(new Timestamp(new Date().getTime()));
            tbManagerEntity.setStatus(0);
            //managerDao.save(tbManagerEntity);

        }catch (Exception e) {
            return "joinClub False";
        }
        return "joinClub Success";
    }

    @Override
    public String DeleteManager(Optional<TbAssociationEntity> associationEntityOptional, Optional<TbUserEntity> userEntityOptional,Optional<TbUserEntity> managerEntityOptional){
        try{
            String message="";

            if(associationEntityOptional.isEmpty()||userEntityOptional.isEmpty()||managerEntityOptional.isEmpty()) {
                if (associationEntityOptional.isEmpty())
                    message += "No such association ";

                if (userEntityOptional.isEmpty())
                    message +=  "No such User ";

                if(managerEntityOptional.isEmpty())
                    message += "No such manager ";

                return message;
            }
            int as_id = associationEntityOptional.get().getId();
            int user_id = userEntityOptional.get().getId();
            int manager_id = managerEntityOptional.get().getId();

            TbManagerEntity UserinAs=managerDao.findByAsIdAndUserId(as_id,user_id);
            TbManagerEntity ManagerinAs=managerDao.findByAsIdAndUserId(as_id,manager_id);

            //未找到社团中存在相关人员
            if(UserinAs==null||ManagerinAs==null) {
                if (UserinAs == null)
                    message = " No such User in association ";
                if (ManagerinAs == null)
                    message += " No such manager in association ";

                return message;
            }
            //自己退出则直接移除自己的manager表
            /*if (user_id==manager_id)
                managerDao.deleteByAsIdAndUserId(as_id,user_id);
            else {
                if (ManagerinAs.getStatus() > UserinAs.getStatus()) {
                    managerDao.deleteByAsIdAndUserId(as_id,user_id);
                } else if (ManagerinAs.getStatus() == 0)
                    return "No permissions！";
            }
            */
/*
            //managerDao.deleteByAsIdAndUserId(as_id,user_id);
*/
            if(user_id!=manager_id&&ManagerinAs.getStatus() == 0)
            {
                return "No permissions！";
            }
        }catch (Exception e) {
            return "DeleteManager False";
        }
        return "DeleteManager Success";
    }

    @Override
    //"查询社团指定成员，0为所有成员，1为管理员和社长，2为社长"
    public List<Integer> ReturnTragetMembers(Optional<TbAssociationEntity> associationEntityOptional,Integer rank) {
/*
        return managerDao.findAllByAsId(as_id);
*/
        List<Integer> user_list = new ArrayList<>();

        if (associationEntityOptional.isEmpty()) {
            System.out.println("No such association");
            return user_list;
        }

        int as_id = associationEntityOptional.get().getId();

        List<TbManagerEntity> ClubMember = managerDao.findAllByAsId(as_id);

        for (TbManagerEntity tbManagerEntity : ClubMember) {
            if (tbManagerEntity.getStatus() >= rank && rank>=0) {
                user_list.add(tbManagerEntity.getUserId());
            }
        }

        System.out.println("ReturnTragetMembers Success!");

        return user_list;
    }

    @Override
    public Optional<TbManagerEntity> ReturnOneMember(Optional<TbAssociationEntity> associationEntityOptional, Optional<TbUserEntity> userEntityOptional)
    {
        Optional<TbManagerEntity> res= Optional.empty();
        try {
            //res=managerDao.findByAsIdAndUserId(as_id,user_id);
            if (associationEntityOptional.isEmpty())
                System.out.println( "No such association");

            if(userEntityOptional.isEmpty())
                System.out.println("No such User");

            if(userEntityOptional.isEmpty()||associationEntityOptional.isEmpty())
                return res;

            int as_id = associationEntityOptional.get().getId();
            int user_id = userEntityOptional.get().getId();

            res = Optional.ofNullable(managerDao.findByAsIdAndUserId(as_id,user_id));//防止没找到

            if(res.isEmpty())
            {
                System.out.println("ReturnOneMember False! No users in association");
            }
            else
                System.out.println("ReturnOneMember Success!");

            return res;
        }
        catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    @Override
    public List<TbManagerEntity> ReturnJoinedClub(Optional<TbUserEntity> userEntityOptional){

        if(userEntityOptional.isEmpty()) {
            System.out.println("No such User");

            List<TbManagerEntity> list=new ArrayList<>();

            return list;

        }

        int user_id = userEntityOptional.get().getId();

        System.out.println("ReturnJoinedClub Success!");

        return managerDao.findAllByUserId(user_id);
    }
    @Override
    public String ChangeRank(Optional<TbAssociationEntity> associationEntityOptional, Optional<TbUserEntity> userEntityOptional,Optional<TbUserEntity> managerEntityOptional){
        try{
            String message="";

            if(associationEntityOptional.isEmpty()||userEntityOptional.isEmpty()||managerEntityOptional.isEmpty()) {
                if (associationEntityOptional.isEmpty())
                    message += "No such association ";

                if (userEntityOptional.isEmpty())
                    message +=  "No such User ";

                if(managerEntityOptional.isEmpty())
                    message += "No such manager ";

                return message;
            }

            Optional<TbManagerEntity> tbUserEntityOptional=this.ReturnOneMember(associationEntityOptional,userEntityOptional);
            Optional<TbManagerEntity> tbManagerEntityOptional=this.ReturnOneMember(associationEntityOptional,managerEntityOptional);

            if (tbManagerEntityOptional.isEmpty()||tbUserEntityOptional.isEmpty()){
                if (tbManagerEntityOptional.isEmpty())
                    message+=" No such Manager in association ";
                if (tbUserEntityOptional.isEmpty())
                    message+=" No such User in association ";

                return message;
            }

            if(tbUserEntityOptional.get().getStatus()<tbManagerEntityOptional.get().getStatus())
            {
                TbManagerEntity tbUserEntity = tbUserEntityOptional.get();
                int rank=tbUserEntity.getStatus();
                if(rank==1)//为管理员，降级
                {
                    tbUserEntity.setStatus(0);
                    managerDao.save(tbUserEntity);
                }
                else if(rank==0)//为用户，升级
                {
                    tbUserEntity.setStatus(1);
                    managerDao.save(tbUserEntity);
                }
            }
            else
                return "No permissions！";

            return "ChangeRank Success!";
        }
        catch (Exception e){
            return "ChangeRank False!";

        }
    }
}
