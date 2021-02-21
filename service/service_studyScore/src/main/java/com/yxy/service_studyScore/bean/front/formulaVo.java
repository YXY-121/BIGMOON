package com.yxy.service_studyScore.bean.front;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class formulaVo {
    //这是一条关于属性的公式。
    private String schoolName;
    private String majorName;
    private String formula;
}
