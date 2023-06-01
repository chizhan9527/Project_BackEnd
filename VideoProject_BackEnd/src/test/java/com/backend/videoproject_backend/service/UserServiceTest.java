package com.backend.videoproject_backend.service;

import com.backend.videoproject_backend.dto.TbManagerEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static com.backend.videoproject_backend.utils.MD5Util.encrypt;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void addUser() {
        ArrayList<TbUserEntity> userEntities=new ArrayList<>();
        String[] as = new String[]{null,"asd","asd","asd","asd","asd"};
        String[] phones=new String[]{"15592228631","11","15592228631","15592228631","15592228631","15592228631"};
        String[] passwords = new String[]{"2132231sd","2132231sd","1","2132231sd","2132231sd","2132231sd",};
        String[] avators=new String[]{"http://123.60.110.3/i/2022/12/03/10kf6jn.png","http://123.60.110.3/i/2022/12/03/10kf6jn.png","http://123.60.110.3/i/2022/12/03/10kf6jn.png","1",
                "http://123.60.110.3/i/2022/12/03/10kf6jn.png","http://123.60.110.3/i/2022/12/03/10kf6jn.png"};
        Timestamp mid=new Timestamp(new Date().getTime());
        Timestamp[] times=new Timestamp[]{mid,mid,mid,mid,null,mid};
        String[] res=new String[]{"userName input error","phone error","password too short"
        ,"url invalid","time no format","register success"};
        for(int i=0;i<as.length;i++){
            TbUserEntity tb1 = new TbUserEntity();
           tb1.setName(as[i]);
           tb1.setPhone(phones[i]);
           tb1.setPassword(encrypt(passwords[i]));
           tb1.setAvator(avators[i]);
           tb1.setCreateTime(times[i]);
           userEntities.add(tb1);
        }
        for(int i=0;i<res.length;i++){
            String cur=userService.addUser(userEntities.get(i));
            if(cur.equals(res[i])){
                System.out.println("第"+i+"项测试成功");
            }
            else{
                System.out.println("第"+i+"项测试失败");
            }
        }

    }
}