package com.yxy.service_self.bean.vo.voServiceSelftestmodule;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)//班长查询某个年份和分数类型
@ApiModel(value="voServiceSelftestmoduleselect1对象", description="自测模块查询一个班的辅助类")
public class voServiceSelftestmoduleselect1 {
    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;
    @ApiModelProperty(value = "院系名字")
    @TableField("department")
    private String department;
    @ApiModelProperty(value = "班级名字")
    @TableField("className")
    private String className;
    @ApiModelProperty(value = "自测分数类型")
    @TableField("selfTestScoreType")
    private String selfTestScoreType;
    @ApiModelProperty(value = "年份",example = "2019")
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。
}

