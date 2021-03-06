package com.behere.system.domain;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class QueryParam implements Param {

    private String idOrNickName;

    private String userId;

    private int gender;

    private int auth;

    public String getIdOrNickName() {
        return idOrNickName;
    }

    public void setIdOrNickName(String idOrNickName) {
        this.idOrNickName = idOrNickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }
}