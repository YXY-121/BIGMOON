package com.yxy.service_self.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 总成绩表
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ServiceTotalscore对象", description="总成绩表")
public class ServiceTotalscore implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "学号Id")
    @TableField("userId")
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

    @ApiModelProperty(value = "学习总成绩")
    @TableField("selfStudyScore")
    private Float selfStudyScore;

    @ApiModelProperty(value = "自测总成绩")
    @TableField("selfTestTotalScore")
    private Float selfTestTotalScore;

    @ApiModelProperty(value = "总成绩")
    @TableField("totalScore")
    private Float totalScore;

    @ApiModelProperty(value = "年份",example = "2019")
    private String  year;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //这里数据库是year,但是在mybatis官网看到一组数据，原来是mybatis不支持YEAR类型的。
    //把实体类Date year;改成 Integer year;
    //数据库仍然是year YEAR字段类型。

    @ApiModelProperty(value = "审核性")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
