package com.yxy.service_self.bean.vo.voAddscoretype;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data//学校和学院不能改因为已经是固定的，如果想改就直接删除在初始化
@ApiModel(value="VoAddscoretypeupdate", description="自测加分大类修改辅助类")
public class VoAddscoretypeupdate {
    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "院系名字")
    @TableField("department")
    private String department;

    @ApiModelProperty(value = "自测分数类型")
    @TableField("selfTestScoreType")
    private String selfTestScoreType;

    @ApiModelProperty(value = "分数比例")
    private Float proportion;

    @ApiModelProperty(value = "年份",example = "2019")//新的那个
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。

    @ApiModelProperty(value = "年份",example = "2019")//老的那个
    private String  oldYear;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "自测截止时间",example = "2019-01-01 ")
    @TableField("studentDeadline")
    private String studentDeadline;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "自测审核截止时间",example = "2019-01-01 ")
    @TableField("managerDeadline")
    private String managerDeadline;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
}
