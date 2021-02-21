package com.yxy.service_userCenter.bean.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserExcel {
    @ExcelProperty(index=0)
    public String userId;

    @ExcelProperty(index=1)
    public String name;

    @ExcelProperty(index=2)
    public String schoolName;

    @ExcelProperty(index=3)
    public String className;

    @ExcelProperty(index=4)
    public String majorName;

    @ExcelProperty(index=5)
    public String department;
    @ExcelProperty(index=6)
    private String phone;











    //密码和role不用上传 默认为学号以及user
}
