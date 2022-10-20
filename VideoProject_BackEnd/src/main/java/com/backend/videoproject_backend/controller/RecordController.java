package com.backend.videoproject_backend.controller;

import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbRecordEntity;
import com.backend.videoproject_backend.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@Api(tags = "运动记录")
public class RecordController {

    @Autowired
    public RecordService recordService;

    @PostMapping("/record")
    @ResponseBody
    @ApiOperation("新建运动记录")
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

    //按userid查找运动记录
    @GetMapping("/record/{user_id}")
    @ResponseBody
    @ApiOperation("按userid查找多条运动记录")
    public List<TbRecordEntity> GetRecord(@PathVariable Integer user_id)
    {
        try{
            return recordService.findByUserId(user_id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
