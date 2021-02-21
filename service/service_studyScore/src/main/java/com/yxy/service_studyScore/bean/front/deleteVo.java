package com.yxy.service_studyScore.bean.front;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class deleteVo {
    private String schoolName;
    private  String department;
    private String deleteItem;
}
