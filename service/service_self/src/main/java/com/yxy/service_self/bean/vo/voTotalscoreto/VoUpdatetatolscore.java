package com.yxy.service_self.bean.vo.voTotalscoreto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VoUpdatetatolscore 对象", description="修改总成绩表")
public class VoUpdatetatolscore {
    @ApiModelProperty(value = "学号Id（如果是班长就传自己学号，普通学生传自己，管理员传自己管理号）")
    @TableField("userId")
    private String userId;
    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "院系名字")
    @TableField("department")
    private String department;

    @ApiModelProperty(value = "专业名字")
    @TableField("majorName")
    private String majorName;

    @ApiModelProperty(value = "班级名字")
    @TableField("className")
    private String className;

    @ApiModelProperty(value = "学习总成绩")
    @TableField("selfStudyScore")
    private Float selfStudyScore;

    @ApiModelProperty(value = "自测总成绩")
    @TableField("selfTestTotalScore")
    private Float selfTestTotalScore;

    @ApiModelProperty(value = "总成绩")
    @TableField("totalScore")
    private Float totalScore;

    @ApiModelProperty(value = "年份")//年份不用修改
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。

    @ApiModelProperty(value = "审核性（如要查询所有就传all(班干或管理员)）")
    private String status;
}
