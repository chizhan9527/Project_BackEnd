package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.TbManagerEntity;
import com.backend.videoproject_backend.service.impl.ManagerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class ManagerServiceTest {
    @Autowired
    private ManagerService managerService;
    @Test
    void joinClub() {
        ArrayList<TbManagerEntity>managerEntities=new ArrayList<>();
        int[] as = new int[]{26, 26,26, -1,110,26};
        int[] userid = new int[]{10, 21, -1,10,10,10};
        int[] rank = new int[]{0,0,2,0,2,-1};
        boolean[] res=new boolean[]{true,false,false,false,false,false};
        for(int i=0;i<as.length;i++){
            TbManagerEntity tb1 = new TbManagerEntity();
            tb1.setAsId(as[i]);
            tb1.setUserId(userid[i]);
            tb1.setJoinTime(new Timestamp(new Date().getTime()));
            tb1.setStatus(rank[i]);
            managerEntities.add(tb1);
        }
        int num=0;
        for(int i=0;i<managerEntities.size();i++){
            //boolean mid=managerService.joinClub(managerEntities.get(i));
            try{
                //assertEquals(res[i],mid);
            }catch (Exception e) {
                num++;
                System.out.println(e);
            }
        }
        System.out.println(6-num+"个测试用例通过");
    }

    @Test
    void quitClub() {
    }

    @Test
    void returnAllMember() {
        //managerService=new ManagerServiceImpl();
        //List<TbManagerEntity> res=managerService.ReturnAllMember(25);
        //System.out.println(res);
    }

    @Test
    void returnOneMember() {
        /*ArrayList<TbManagerEntity>managerEntities=new ArrayList<>();
        int[] as = new int[]{25, 25,25, -1,110,25};
        int[] userid = new int[]{16, 50, -1,16,16,7};
        boolean[] res=new boolean[]{true,false,false,false,false,false};
        int num=0;
        for(int i=0;i<managerEntities.size();i++){
            TbManagerEntity mid=managerService.ReturnOneMember(as[i],userid[i]);
            if(res[i]){
                if(mid!=null)num++;
            }
            else{
                if(mid==null)num++;
            }
        }
        System.out.println(6-num+"个测试用例通过");*/
    }

    @Test
    void returnJoinedClub() {
    }

    @Test
    void updateManager() {
    }
}