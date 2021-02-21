package com.yxy.service_userCenter.bean.front;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class userMegVo {
    private String schoolName;
    private String userId;
    private  String newPwd;
    private String code;

}
