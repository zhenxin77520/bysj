package com.depth.management.model;

import com.depth.management.common.BaseEntity;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 考勤表
 */
@Table(name = "attendance")
public class Attendance extends BaseEntity {
    private static final long serialVersionUID = -7996308356707681063L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 员工ID
     */
    private Long empId;
    /**
     * 上班时间
     */
    private Date startTime;
    /**
     * 下班时间
     */
    private Date endTime;

    public Attendance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", empId=" + empId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}