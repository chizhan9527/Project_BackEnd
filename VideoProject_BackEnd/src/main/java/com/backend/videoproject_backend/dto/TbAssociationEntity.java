package com.backend.videoproject_backend.dto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_association", schema = "bili", catalog = "")
public class TbAssociationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "association_name")
    private String associationName;
    @Basic
    @Column(name = "establish_time")
    private Timestamp establishTime;
    @Basic
    @Column(name = "member_num")
    private int memberNum;
    @Basic
    @Column(name = "association_desc")
    private String associationDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public Timestamp getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(Timestamp establishTime) {
        this.establishTime = establishTime;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getAssociationDesc() {
        return associationDesc;
    }

    public void setAssociationDesc(String associationDesc) {
        this.associationDesc = associationDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbAssociationEntity that = (TbAssociationEntity) o;

        if (id != that.id) return false;
        if (memberNum != that.memberNum) return false;
        if (associationName != null ? !associationName.equals(that.associationName) : that.associationName != null)
            return false;
        if (establishTime != null ? !establishTime.equals(that.establishTime) : that.establishTime != null)
            return false;
        if (associationDesc != null ? !associationDesc.equals(that.associationDesc) : that.associationDesc != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (associationName != null ? associationName.hashCode() : 0);
        result = 31 * result + (establishTime != null ? establishTime.hashCode() : 0);
        result = 31 * result + memberNum;
        result = 31 * result + (associationDesc != null ? associationDesc.hashCode() : 0);
        return result;
    }
}
