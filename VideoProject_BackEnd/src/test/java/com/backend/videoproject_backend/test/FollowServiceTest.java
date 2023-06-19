package com.backend.videoproject_backend.test;

import com.backend.videoproject_backend.dao.FollowDao;
import com.backend.videoproject_backend.service.impl.FollowServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FollowServiceTest {

    @Autowired
    FollowServiceImpl followService;
    @Test
    public void testFollow() {
        String output;
        Integer num=1;
        //用例1
        output = followService.doFollow(-1, -1);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例2
        output = followService.doFollow(-1, 156734);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例3
        output = followService.doFollow(-1, 6);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例4
        output = followService.doFollow(-1, 100000000);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例5
        output = followService.doFollow(156734, -1);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例6
        output = followService.doFollow(156734, 156734);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例7
        output = followService.doFollow(156734, 6);
        if (!Objects.equals(output, "Follow success")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例8
        output = followService.doFollow(156734, 100000000);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例9
        output = followService.doFollow(4, -1);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例10
        output = followService.doFollow(4, 156734);
        if (!Objects.equals(output, "Follow success")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例11
        output = followService.doFollow(4, 6);
        if (!Objects.equals(output, "Follow success")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例12
        output = followService.doFollow(4, 100000000);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例13
        output = followService.doFollow(100000000, -1);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例14
        output = followService.doFollow(100000000, 156734);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例15
        output = followService.doFollow(100000000, 6);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        num++;
        //用例16
        output = followService.doFollow(100000000, 100000000);
        if (!Objects.equals(output, "Invalid input")) {
            System.out.println("The "+num+" Test Failed");
            return;
        }
        System.out.println("Total "+num+" Test Success");
    }

    @Test
    public void testFollowOrNot(){
        int[] userId = {-1,-1,-1,-1,-1,156734,156734,156734,156734,156734,4,4,4,4,4,10,10,10,10,10,100000000,100000000,100000000,100000000,100000000};
        int[] followUserId = {-1,156734,6,8,100000000,-1,156734,6,8,100000000,-1,156734,6,8,100000000,-1,156734,6,8,100000000,-1,156734,6,8,100000000};
        String[] expectedResult={"Invalid input","Invalid input","Invalid input",
                "Invalid input","Invalid input","Invalid input","Invalid input","False","False",
                "Invalid input","Invalid input","False","True","True","Invalid input",
                "Invalid input","False","True","False","Invalid input","Invalid input",
                "Invalid input","Invalid input","Invalid input","Invalid input"};
        int num=0;
        for(int i=0;i<userId.length;i++){
            String result=followService.followOrNot(userId[i],followUserId[i]);
            if(!Objects.equals(result, expectedResult[i]))
            {
                System.out.println(result+"  "+expectedResult[i]);
                System.out.println("The "+(num+1)+" Test Failed");
                return;
            }
            num++;
        }
        System.out.println("Total "+num+" Test Success");
    }
}
