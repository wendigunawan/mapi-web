package com.mapi.ihrd.module.aauth.model;

import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.config.Constant;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constant.TABLE_NAME_USER_TOKEN)
public class UserToken extends BaseModel {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_date")
    private Date expiredDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }


}
