package com.backend.videoproject_backend.dto;

import javax.persistence.*;

@Entity
@Table(name = "v_user_record", schema = "bili", catalog = "")
public class VUserRecordEntity {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "sum_distance")
    private Long sumDistance;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(Long sumDistance) {
        this.sumDistance = sumDistance;
    }
}
