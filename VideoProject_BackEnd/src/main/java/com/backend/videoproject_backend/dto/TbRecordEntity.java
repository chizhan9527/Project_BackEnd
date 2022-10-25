package com.backend.videoproject_backend.dto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_record", schema = "bili", catalog = "")
public class TbRecordEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "distance")
    private Integer distance;
    @Basic
    @Column(name = "pace")
    private Integer pace;
    @Basic
    @Column(name = "steps")
    private Integer steps;
    @Basic
    @Column(name = "steps_frequency")
    private Integer stepsFrequency;
    @Basic
    @Column(name = "time")
    private String time;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getPace() {
        return pace;
    }

    public void setPace(Integer pace) {
        this.pace = pace;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getStepsFrequency() {
        return stepsFrequency;
    }

    public void setStepsFrequency(Integer stepsFrequency) {
        this.stepsFrequency = stepsFrequency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
