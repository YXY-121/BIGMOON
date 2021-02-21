package com.yxy.service_studyScore.bean;

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
 * 
 * </p>
 *
 * @author yxy
 * @since 2020-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ServiceUser对象", description="")
public class ServiceUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("userId")
    private String userId;

    @TableField("name")
    private String name;

    @TableField("className")
    private String className;

    @TableField("schoolName")
    private String schoolName;

    @TableField("password")
    private String password;

    @TableField("department")
    private String department;

    @TableField("majorName")
    private String majorName;

    @TableField("roleId")
    private String roleId;

    @TableField("gmt_createTime")
    private Date gmtCreatetime;

    private Date gmtModified;


}
