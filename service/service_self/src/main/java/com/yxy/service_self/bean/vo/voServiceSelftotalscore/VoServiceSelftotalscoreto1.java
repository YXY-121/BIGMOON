package com.yxy.service_self.bean.vo.voServiceSelftotalscore;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data//这个辅助类管理员班干普通学生都可以用
@ApiModel(value="VoServiceSelftotalscoreto1", description="查询自测返回辅助类无年份")
public class VoServiceSelftotalscoreto1 {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "学号Id")
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


    @ApiModelProperty(value = "自测总成绩")
    @TableField("selfTestTotalScore")
    private Float selfTestTotalScore;



}
