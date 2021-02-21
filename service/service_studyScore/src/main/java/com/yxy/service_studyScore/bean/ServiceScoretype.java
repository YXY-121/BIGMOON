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
@ApiModel(value="ServiceScoretype对象", description="")
public class ServiceScoretype implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "scoreTypeId", type = IdType.ASSIGN_ID)
    private String scoreTypeId;

    @TableField("scoreTypeName")
    private String scoreTypeName;

    @TableField("schoolName")
    private String schoolName;

    @TableField("score")
    @ApiModelProperty(value = "大类占比的分值")
    private Float score;

    @TableField("department")
    private String department;


}
