package com.yxy.service_userCenter.bean.front;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class userVo {
    private String schoolName;
    private String department;
    private String major;
    private int year;
    private String userId;
}
