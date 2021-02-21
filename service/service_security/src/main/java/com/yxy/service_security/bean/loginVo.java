package com.yxy.service_security.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class loginVo {
    private String userId;
    private String schoolName;
    private  String password;
}
