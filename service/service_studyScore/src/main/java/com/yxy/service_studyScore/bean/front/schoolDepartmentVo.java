package com.yxy.service_studyScore.bean.front;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//删除和查看都用这个
public class schoolDepartmentVo {
    private String schoolName;
    private String department;
}
