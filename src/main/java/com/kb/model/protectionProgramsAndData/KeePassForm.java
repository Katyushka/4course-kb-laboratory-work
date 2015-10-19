package com.kb.model.protectionProgramsAndData;

import java.io.Serializable;

/**
 * Created by user on 19.10.15.
 */
public class KeePassForm implements Serializable {
    private String title = "";
    private String userName="";
    private String password="";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
