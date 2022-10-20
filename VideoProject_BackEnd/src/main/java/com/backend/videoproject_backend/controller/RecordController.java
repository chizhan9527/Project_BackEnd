package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbRecordEntity;
import com.backend.videoproject_backend.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@Api(tags = "运动记录")
public class RecordController {

    @Autowired
    public RecordService recordService;

    @PostMapping
    @ResponseBody
    @ApiOperation("新建一个运动记录")
    public String PostRecord(int user_id,int distance,int pace,int steps,int steps_frequency,String time)
    {
        try{
            TbRecordEntity tbRecordEntity = new TbRecordEntity();
            tbRecordEntity.setUserId(user_id);
            tbRecordEntity.setDistance(distance);
            tbRecordEntity.setPace(pace);
            tbRecordEntity.setSteps(steps);
            tbRecordEntity.setStepsFrequency(steps_frequency);
            tbRecordEntity.setTime(time);
            tbRecordEntity.setCreateTime(new Timestamp(new Date().getTime()));
            recordService.addRecord(tbRecordEntity);
            return "ok";
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
