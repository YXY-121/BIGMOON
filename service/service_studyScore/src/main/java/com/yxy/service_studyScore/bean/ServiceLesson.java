package com.yxy.service_studyScore.bean;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ServiceLesson对象", description="")
public class ServiceLesson implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "学校 识别一条课程")
    @TableField("schoolName")
    private String schoolName;

    @ApiModelProperty(value = "专业 识别一条课程")
    @TableField("department")
    private String department;

//    @ApiModelProperty(value = "专业 识别一条课程")
//    @TableField("majorName")
//    private String majorName;

 /*   @ApiModelProperty(value = "专业 识别一条课程")
    @TableField("year")
    private String year;*/

    @ApiModelProperty(value = "课程名字")
    @TableField("lessonName")
    private String lessonName;

    @ApiModelProperty(value = "某个学校某个专业的课程基本信息")
    @TableId(value = "lessonId", type = IdType.ASSIGN_ID)
    private String lessonId;

    @ApiModelProperty(value = "大类别 占比多少")
    @TableField("scoreType")
    private String scoreType;






}
