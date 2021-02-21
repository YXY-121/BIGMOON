package com.yxy.service_studyScore.bean.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class LessonExcel {
    @ExcelProperty(index=0)
    public String lessonName;









}
