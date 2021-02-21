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
 * @since 2020-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ServiceAdmin对象", description="")
public class ServiceAdmin implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "adminId", type = IdType.ASSIGN_ID)
    private String adminId;

    @TableField("schoolName")
    private String schoolName;

    @TableField("majorName")
    private String majorName;

    private String grade;


}
