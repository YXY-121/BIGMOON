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
@ApiModel(value="ServiceLessonattribute对象", description="")
public class ServiceLessonattribute implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "某个学校某个年级的课程属性")
    @TableId(value = "attributeId", type =IdType.ASSIGN_ID)
    private String attributeId;

    @ApiModelProperty(value = "属性名字")
    @TableField("attributeName")
    private String attributeName;

    @TableField("schoolName")
    private String schoolName;

    @TableField("department")
    private String department;



}
