package com.backend.videoproject_backend.test;

import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void addUser(){
        String[] username = new String[]{null,"username1","username2","username3","username4","username5","username0"};
        String[] phone = new String[]{"13812345678","1381234","138123456789","1381234abcd","13812345678","13812345678","13812345678"};
        String[] avatar = new String[]{"0000000000","0000000000","0000000000","0000000000","0000000000","0","0000000000"};
        String[] password = new String[]{"password","password","password","password","123","password","password"};
        String[] res=new  String[]{"userName input error","phone error","phone error","phone error","password too short","url invalid","register success"};
        for(int i=0;i<username.length;i++){
            TbUserEntity tbUserEntity = new TbUserEntity();
            tbUserEntity.setName(username[i]);
            tbUserEntity.setPhone(phone[i]);
            tbUserEntity.setPassword(password[i]);
            tbUserEntity.setAvator(avatar[i]);
            tbUserEntity.setCreateTime(new Timestamp(new Date().getTime()));

            int num=1;
            String output = userService.addUser(tbUserEntity);

            if (!Objects.equals(output, res[i])) {
                System.out.println("The "+num+" Test Failed");
                System.out.println("Predict:" + res[i]+" But: "+output);
            }

            num++;
        }
    }

    @Test
    public void testDelete(){
        int[] ids = new int[]{4,30,-1,1000000000};
        for(int i=0;i<ids.length;i++){
            System.out.println(userService.deleteUser2(ids[i]));
        }
    }

    @Test
    public void testUpdate(){
        int[] ids = new int[]{4, 4, 30, 30, 4, 4, 30, 30, 4, 4, 30, 30};
        String[] email = new String[]{"13464885811@qq.com", "13464885811", "13464885811@qq.com", "13464885811", "13464885811@qq.com", "13464885811", "13464885811@qq.com", "13464885811", "13464885811@qq.com", "13464885811", "13464885811@qq.com", "13464885811"};
        int[] gender = new int[]{1, 0, 3, 1, 0, 3, 1, 0, 3, 1, 0, 3};
        for(int i=0;i<ids.length;i++){
            System.out.println(userService.updateUser2(ids[i],gender[i],email[i]));
        }

    }
}
