package com.backend.videoproject_backend.dto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_manager", schema = "bili", catalog = "")
public class TbManagerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "as_id")
    private int asId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "status")
    private int status;
    @Basic
    @Column(name = "join_time")
    private Timestamp joinTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAsId() {
        return asId;
    }

    public void setAsId(int asId) {
        this.asId = asId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Timestamp joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbManagerEntity that = (TbManagerEntity) o;

        if (id != that.id) return false;
        if (asId != that.asId) return false;
        if (userId != that.userId) return false;
        if (status != that.status) return false;
        if (joinTime != null ? !joinTime.equals(that.joinTime) : that.joinTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + asId;
        result = 31 * result + userId;
        result = 31 * result + status;
        result = 31 * result + (joinTime != null ? joinTime.hashCode() : 0);
        return result;
    }
}
