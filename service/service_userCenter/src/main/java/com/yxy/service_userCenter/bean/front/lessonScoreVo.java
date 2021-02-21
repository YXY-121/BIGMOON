package com.yxy.service_userCenter.bean.front;

import com.yxy.service_userCenter.bean.ColumnScore;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class lessonScoreVo {
    private String schoolName;
    private String department;
    private String major;
    private int year;
    private String userId;

    private List<ColumnScore> columnScores;
}
