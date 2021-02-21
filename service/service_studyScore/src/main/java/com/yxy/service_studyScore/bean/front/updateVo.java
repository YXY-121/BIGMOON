package com.yxy.service_studyScore.bean.front;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class updateVo {
    private String schoolName;
    private String department;
    private String role;
    private String changedItem;
    private String newValue;
}
