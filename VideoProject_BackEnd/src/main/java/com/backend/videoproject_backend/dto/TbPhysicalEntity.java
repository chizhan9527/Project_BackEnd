package com.backend.videoproject_backend.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tb_physical", schema = "bili", catalog = "")
public class TbPhysicalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "height")
    private Integer height;
    @Basic
    @Column(name = "weight")
    private Integer weight;
    @Basic
    @Column(name = "BMI")
    private Double bmi;
    @Basic
    @Column(name = "bust")
    private Integer bust;
    @Basic
    @Column(name = "waist")
    private Integer waist;
    @Basic
    @Column(name = "hipline")
    private Integer hipline;
    @Basic
    @Column(name = "modification_time")
    private Timestamp modificationTime;

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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Integer getBust() {
        return bust;
    }

    public void setBust(Integer bust) {
        this.bust = bust;
    }

    public Integer getWaist() {
        return waist;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public Integer getHipline() {
        return hipline;
    }

    public void setHipline(Integer hipline) {
        this.hipline = hipline;
    }

    public Timestamp getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Timestamp modificationTime) {
        this.modificationTime = modificationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbPhysicalEntity that = (TbPhysicalEntity) o;
        return id == that.id && userId == that.userId && Objects.equals(height, that.height) && Objects.equals(weight, that.weight) && Objects.equals(bmi, that.bmi) && Objects.equals(bust, that.bust) && Objects.equals(waist, that.waist) && Objects.equals(hipline, that.hipline) && Objects.equals(modificationTime, that.modificationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, height, weight, bmi, bust, waist, hipline, modificationTime);
    }
}
