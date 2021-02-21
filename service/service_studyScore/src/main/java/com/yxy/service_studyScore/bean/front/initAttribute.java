package com.yxy.service_studyScore.bean.front;

import com.yxy.service_studyScore.bean.ServiceLessonattribute;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class initAttribute {
    private String schoolName;
    private String department;
    private List<String> lessonattributes;
}
