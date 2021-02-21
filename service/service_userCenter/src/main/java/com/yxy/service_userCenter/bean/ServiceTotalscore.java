package com.yxy.service_userCenter.bean;

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
 * @since 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ServiceTotalscore对象", description="总成绩表")
public class ServiceTotalscore implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "学号Id")
    @TableId(value = "userId", type = IdType.ASSIGN_ID)
    private String userId;

    @ApiModelProperty(value = "学校名字")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "学习总成绩")
    @TableField("selfStudyScore")
    private Float studyscore;

    @ApiModelProperty(value = "自测总成绩")
    @TableField("selfTestTotalScore")
    private Integer selftotalscore;


    @ApiModelProperty(value = "总成绩")
    @TableField("totalscore")
    private Integer totalscore;


    @ApiModelProperty(value = "年份")
    @TableField("year")
    private Integer year;

    @ApiModelProperty(value = "院系")
    @TableField("department")
    private String department;


/*
    @ApiModelProperty(value = "专业")
    @TableField("major")
    private String major;
*/

    @ApiModelProperty(value = "审核情况")
    @TableField("status")
    private String status;


    @ApiModelProperty(value = "创建时间")
   @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
