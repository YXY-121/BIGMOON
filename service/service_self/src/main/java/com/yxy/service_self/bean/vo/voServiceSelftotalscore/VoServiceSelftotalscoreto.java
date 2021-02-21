package com.yxy.service_self.bean.vo.voServiceSelftotalscore;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data//专门给学生或者班干查自己用的
@ApiModel(value="VoServiceSelftotalscoreto", description="查询自测返回辅助类有年份")
public class VoServiceSelftotalscoreto {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "学号Id")
    private String userId;

    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "专业名字")
    @TableField("majorName")
    private String majorName;

    @ApiModelProperty(value = "班级名字")
    @TableField("className")
    private String className;
    @ApiModelProperty(value = "院系名字")
    @TableField("department")
    private String department;

    @ApiModelProperty(value = "自测总成绩")
    @TableField("selfTestTotalScore")
    private Float selfTestTotalScore;

    @ApiModelProperty(value = "年份",example = "2019")
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。
}

