package com.yxy.service_self.bean.vo.voServiceSelftotalscore;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data//专门给管理员查看某个学校某个院系某一年某个专业所有同学的自测成绩
@ApiModel(value="VoServiceSelftotalscore1", description="查询自测辅助类")
public class VoServiceSelftotalscore2 {
    private static final long serialVersionUID=1L;

  /*  @ApiModelProperty(value = "学号Id")
    private String userId;*/

    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;
    @ApiModelProperty(value = "院系名字")
    @TableField("department")
    private String department;
    @ApiModelProperty(value = "专业名字")
    @TableField("majorName")
    private String majorName;
/*
    @ApiModelProperty(value = "班级名字")
    @TableField("className")
    private String className;*/

    @ApiModelProperty(value = "年份",example = "2019")
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。
}
