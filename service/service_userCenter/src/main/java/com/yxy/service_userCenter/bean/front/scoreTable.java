package com.yxy.service_userCenter.bean.front;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
public class scoreTable {
    private String lessonName;
    private List<lessonAttributeScore> lessonAttributeValue;
}
