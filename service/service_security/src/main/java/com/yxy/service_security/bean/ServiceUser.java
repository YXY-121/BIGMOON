package com.yxy.service_security.bean;

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
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ServiceUser对象", description="")
public class ServiceUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "userId")
    private String userId;

    @TableField("password")
    private String password;

    @TableField("name")
    private String name;

    @TableField("className")
    private String className;


    @TableField("schoolName")
    private String schoolName;


    @TableField("majorName")
    private String majorName;

    @TableField("roleId")
    private String roleId;
    public ServiceUser(){

    }
   /* public ServiceUser(String userId,String majorName,String schoolName)
    {
        this.majorName=majorName;
        this.schoolName=schoolName;
        this.userId=userId;
    }
*/

}
