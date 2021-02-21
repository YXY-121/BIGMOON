package com.yxy.service_self.bean.vo.voTotalscoreto;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value="VoServiceTotalscore", description="总表按学号班级学校年份查询所有")
public class VoServiceTotalscore {
    @ApiModelProperty(value = "学号Id（如果是班长就传自己学号，普通学生传自己，管理员传自己管理号）")
    @TableField("userId")
    private String userId;
    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "专业名字")
    @TableField("majorName")
    private String majorName;
    @ApiModelProperty(value = "院系名字")
    @TableField("department")
    private String department;

    @ApiModelProperty(value = "班级名字")
    @TableField("className")
    private String className;

    @ApiModelProperty(value = "年份")
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。

    @ApiModelProperty(value = "审核性（如要查询所有就传all(班干或管理员)）")
    private String status;
}
