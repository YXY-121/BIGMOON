package com.yxy.service_userCenter.bean.front;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class lessonAttributeScore {
    private  String attribute;
    private float score;
}
