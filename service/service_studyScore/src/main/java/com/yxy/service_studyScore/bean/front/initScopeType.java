package com.yxy.service_studyScore.bean.front;

import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import com.yxy.service_studyScore.bean.ServiceScoretype;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
public class initScopeType {
    private String schoolName;
    private String department;
    private Map<String,Float> scoretypes;
}
