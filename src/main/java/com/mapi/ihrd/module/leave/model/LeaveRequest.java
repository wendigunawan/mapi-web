package com.mapi.ihrd.module.leave.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mapi.ihrd.config.Constant;
import com.mapi.ihrd.module.employee.model.Employee;

import javax.persistence.*;


@Entity
@Table(name = Constant.TABLE_NAME_LEAVE_REQUEST)
public class LeaveRequest extends LeaveRequestBaseModel {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_by")
    private Employee requestBy;

    public Employee getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(Employee requestBy) {
        this.requestBy = requestBy;
    }


    public enum Type {

        LEAVE("leave"), SICK("sick"), PERMISSION("permission"), LATE("late");

        private Type(String val) {

        }

    }

    public enum Status {

        NEW("new"), APPROVED("approved"), REJECTED("rejected");

        private Status(String val) {

        }


    }


}
