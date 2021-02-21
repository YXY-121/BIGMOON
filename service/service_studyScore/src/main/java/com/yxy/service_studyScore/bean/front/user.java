package com.yxy.service_studyScore.bean.front;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class user {
    public String schoolName;
 public String loginName;//userid
 public String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
